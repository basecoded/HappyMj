package com.s4game.server.stage.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s4game.core.event.IEventService;
import com.s4game.server.bus.stagecontroll.command.StageControllCommands;
import com.s4game.server.stage.event.publish.RoleEnterStageEvent;
import com.s4game.server.stage.event.publish.RoleLeaveStageEvent;
import com.s4game.server.stage.event.publish.StageCreateEvent;
import com.s4game.server.stage.model.core.element.impl.state.OfflineState;
import com.s4game.server.stage.model.core.stage.ElementType;
import com.s4game.server.stage.model.core.stage.IStage;
import com.s4game.server.stage.model.core.stage.Point;
import com.s4game.server.stage.model.element.role.IRole;
import com.s4game.server.stage.model.element.role.RoleFactory;
import com.s4game.server.stage.room.RoomStageFactory;
import com.s4game.server.stage.service.IStageService;
import com.s4game.server.stage.swap.StageMsgSender;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月17日 上午11:15:41
 *
 */
@Service
public class StageServiceImpl implements IStageService {

    @Autowired
    private IEventService eventService;
    
    @Autowired
    private StageMsgSender stageMsgSender;
    
    @Autowired
    private RoleFactory roleFactory;
    
    @Autowired
    private RoomStageFactory stageFactory;
    
    private ConcurrentMap<String, IStage> stageMap = new ConcurrentHashMap<String, IStage>();
    
    @Override
    public boolean checkAndCreateStage(String stageId, String mapId) {
        IStage stage = stageMap.get(stageId);
        if( null == stage ) {
            stage = stageFactory.create(stageId, mapId);
            stageMap.put(stageId, stage);
            
            eventService.publish(new StageCreateEvent(mapId, stageId));
            return true;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S extends IStage> S getStage(String stageId) {
        return (S) stageMap.get(stageId);
    }

    @Override
    public void removeStage(String stageId) {
        stageMap.remove(stageId);
    }
    
    @Override
    public boolean exist(String stageId) {
        return null != getStage(stageId);
    }

    @Override
    public boolean stageCanEnter(String stageId) {
        IStage stage = getStage(stageId);
        if( null == stage ) {
            return false;
        }
        return true;
    }

    @Override
    public Point getPosition(String stageId, String roleId) {
        IStage stage = getStage(stageId);
        IRole role = stage.getElement(roleId, ElementType.ROLE);
        
        if( null == role ) {
            return null;
        }
        return role.getPosition();
    }

    @Override
    public void roleLeaveStage(String stageId, String roleId) {
        IStage stage = getStage(stageId);
        if( null == stage ) {
            return;
        }
        
        IRole role = stage.getElement(roleId, ElementType.ROLE);
        if( null == role ) {
            return;
        }
        
        role.getEventManager().fireLogoutEvent();
        eventService.publish(new RoleLeaveStageEvent(roleId, stageId));
        
        role.getStateManager().add(new OfflineState());
        stage.leave(role);
    }
    
    @Override
    public boolean roleCanChangeMap(String roleId, String stageId) {
        IStage stage = getStage(stageId);
        if( null == stage ) {
            return false;
        }
        
        IRole role = stage.getElement(roleId, ElementType.ROLE);
        if( null == role ) {
            return false;
        }
        
        return role.getStage().isCopy() || !role.getStateManager().isDead();
    }

    @Override
    public void roleEnterStage(String stageId, String roleId, String mapId, int x, int y) {
        IStage stage = getStage(stageId);
        IRole role = stage.getElement(roleId, ElementType.ROLE);
        
        //已经在场景内
        if( role != null ) {
            //stageMsgSender.sned2One(StageControllCommands.CHANGE_STAGE, roleId, stageId, new Object[]{1, stageId, mapId});
            return;
        }
        
        //填充，初始化 role 数据
        role = roleFactory.create(roleId, stage);
        //stageMsgSender.sned2One(StageControllCommands.CHANGE_STAGE, roleId, stageId, new Object[]{1, stageId, mapId});
        
        stage.enter(role, x, y);
        eventService.publish(new RoleEnterStageEvent(stageId, mapId, roleId));
        
        role.getEventManager().fireLoginEvent();
    }

    @Override
    public void addStageCopy(IStage stageCopy) {
        stageMap.put(stageCopy.getId(), stageCopy);
    }

}

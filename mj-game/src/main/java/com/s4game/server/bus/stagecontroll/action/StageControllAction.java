package com.s4game.server.bus.stagecontroll.action;

import static com.s4game.server.bus.stagecontroll.command.StageControllCommands.APPLY_CHANGE_STAGE;
import static com.s4game.server.bus.stagecontroll.command.StageControllCommands.CHANGE_STAGE;
import static com.s4game.server.bus.stagecontroll.command.StageControllCommands.INNER_APPLY_CHANGE_COPY;
import static com.s4game.server.bus.stagecontroll.command.StageControllCommands.LOGIN;

import javax.annotation.Resource;

import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.core.message.Message;
import com.s4game.server.bus.stagecontroll.output.StageControllOutput;
import com.s4game.server.bus.stagecontroll.service.IStageControllService;
import com.s4game.server.bus.swap.BusMsgSender;
import com.s4game.server.login.commond.LoginCommands;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月2日 下午5:29:09
 *
 */
@ActionWorker
public class StageControllAction {

    @Resource
    private BusMsgSender busMsgSender;
    
    @Resource
    private IStageControllService stageControllService;
    
    @ActionMapping(mapping = LOGIN)
    public void login(Message message) {
        String roleId = message.getRoleId();
        
        stageControllService.login(roleId);
        busMsgSender.send2One(LoginCommands.LOGIN_IN, roleId, StageControllOutput.login(roleId));
    }
    
//    @ActionMapping(mapping = APPLY_CHANGE_STAGE)
//    public void applyChangeMap(Message message) {
//        String roleId = message.getRoleId();
//        
//        Object[] result = stageControllService.applyChangeMapAfterLogin(roleId);
//        busMsgSender.send2One(APPLY_CHANGE_STAGE, roleId, result);
//    }
    
    @ActionMapping(mapping = CHANGE_STAGE)
    public void changeMap(Message message) {
        String roleId = message.getRoleId();
        
        stageControllService.changeMap(roleId);
    }
    
    /**
     * 请求进入普通关卡
     * 
     * <p> 通用副本，特殊副本需要自定义协议
     * 
     * @param message
     */
    @ActionMapping(mapping = INNER_APPLY_CHANGE_COPY)
    public void applyChangeCopy(Message message) {
        String roleId = message.getRoleId();
        
        Object[] data = (Object[])message.getData();
        String mapId = (String)data[0];
        int x = (Integer)data[1];
        int y = (Integer)data[2];
        Object[] additionalData = (Object[])data[3];
        
        Object result = stageControllService.applyChangeCopy(roleId, mapId, x, y, additionalData);
        
        busMsgSender.send2One(APPLY_CHANGE_STAGE, roleId, result);
    }
}

package com.s4game.server.bus.stagecontroll.moduleinit;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.event.IEventHandler;
import com.s4game.server.bus.share.moduleinit.BusModuleInit;
import com.s4game.server.bus.stagecontroll.StageControllModuleInfo;
import com.s4game.server.bus.stagecontroll.command.StageControllCommands;
import com.s4game.server.bus.stagecontroll.event.subscribe.RoleLoginHandler;
import com.s4game.server.bus.stagecontroll.event.subscribe.RoleLogoutHandler;
import com.s4game.server.bus.stagecontroll.service.IStageControllService;
import com.s4game.server.share.moduleinit.Group;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月2日 下午5:35:22
 *
 */
@Component
public class StageControllMuduleInit extends BusModuleInit {

    @Resource
    private RoleLoginHandler roleLoginHandler;
    
    @Resource
    private RoleLogoutHandler roleLogoutHandler;
    
    @Resource
    private IStageControllService stageControllService;
    
    @Override
    protected InCmd getInCmd() {
        String[] cmd = new String[] {
                StageControllCommands.LOGIN, 
                StageControllCommands.APPLY_CHANGE_STAGE, 
                StageControllCommands.CHANGE_STAGE,
                StageControllCommands.INNER_LEAVE_STAGE,
                StageControllCommands.INNER_ENTER_STAGE
        };
        
        return new InCmd(StageControllModuleInfo.MODULE_INFO, Group.STAGE_CONTROL.name, cmd);
    }
    
    protected IEventHandler[] getEventHandlers() {
        return new IEventHandler[] { this.roleLoginHandler, this.roleLogoutHandler };
    }

    @Override
    public void moduleInit() {
        stageControllService.serverStartInitStage();
    }
    
    @Override
    public int getOrder() {
        return 201;
    }
    
}

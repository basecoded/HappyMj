package com.s4game.server.stage.action;

import static com.s4game.server.stage.command.StageCommands.INNER_ENTER_STAGE;
import static com.s4game.server.stage.command.StageCommands.INNER_LEAVE_STAGE;

import org.springframework.beans.factory.annotation.Autowired;

import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.core.message.Message;
import com.s4game.server.stage.service.IStageService;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年9月7日 上午11:41:28
 *
 */
@ActionWorker
public class StageAction {

    @Autowired
    private IStageService stageService;
    
    @ActionMapping(mapping = INNER_ENTER_STAGE)
    public void enter(Message message) {
        String roleId = message.getRoleId();
        
        Object[] data = (Object[]) message.getData();
        String stageId = (String)data[0];
        String mapId = (String)data[1];
        int x = (Integer)data[2];
        int y = (Integer)data[3];
        
        stageService.roleEnterStage(stageId, roleId, mapId, x, y);
    }
    
    @ActionMapping(mapping = INNER_LEAVE_STAGE)
    public void leave(Message message) {
        String roleId = message.getRoleId();
        
        Object[] data = (Object[]) message.getData();
        String stageId = (String)data[0];
        
        stageService.roleLeaveStage(stageId, roleId);
    }
    
}

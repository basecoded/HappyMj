package com.s4game.server.stage.swap;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.message.Message;
import com.s4game.core.message.Message.DestType;
import com.s4game.core.message.Message.FromType;
import com.s4game.server.io.IoConstants;
import com.s4game.server.message.IMsgDispatcher;
import com.s4game.server.message.manager.SwapManager;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月21日 下午4:45:56
 *
 */
@Component
public class StageMsgSender {

    @Resource
    private SwapManager swapManager;
    
    @Resource
    private IMsgDispatcher stageDispatcher; 
    
    public void send2One(String command, String roleId, String stageId, Object data) {
        //Object[] message = new Object[]{command, data, DestType.CLIENT.getValue(), FromType.STAGE.getValue(), 1, null, roleId, stageId, 0, null};
        
        Message message = new Message(command, data, FromType.STAGE, DestType.CLIENT, roleId);
        message.setStageId(stageId);
        message.setRoute(IoConstants.SEND_TO_ONE);// send one player
        
        swapManager.swap(message);
    }
    
    public void send2Many(String command, String roleId, String stageId, String[] roleIds, Object data) {
        Message message = new Message(command, data, FromType.STAGE, DestType.CLIENT, roleId);
        message.setStageId(stageId);
        message.setRoleIds(roleIds);
        
        message.setRoute(IoConstants.SEND_TO_MANY);
        
        swapManager.swap(message);
    }
    
    public void send2StageControl(String command, String roleId, Object data) {
        //Object[] message = new Object[]{command, data, DestType.STAGE_CONTROL.getValue(), FromType.STAGE.getValue(), 1, null, roleId, null, 0, null};
    
        Message message = new Message(command, data, FromType.STAGE, DestType.STAGE_CONTROL, roleId);
        stageDispatcher.in(message);
    }
}

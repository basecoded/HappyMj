package com.s4game.server.public_.nodecontrol.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.core.message.Message;
import com.s4game.server.public_.nodecontrol.command.NodeControlCommands;
import com.s4game.server.public_.nodecontrol.service.INodeControlService;

@ActionWorker
public class NodeControlAction {
    
    @Autowired
    private INodeControlService nodeControlService;

    @ActionMapping(mapping = NodeControlCommands.ROLE_IN)
    public void roleIn(Message message) {
        String roleId = message.getRoleId();
        String ip = message.getIp();

        this.nodeControlService.change2online(roleId);
        this.nodeControlService.nodeLogin(roleId, ip);
    }

    @ActionMapping(mapping = NodeControlCommands.ROLE_OUT)
    public void roleOut(Message message) {
        JSONObject data = (JSONObject)message.getData();
        
        String roleId = data.getString("roleId");
        String str2 = "";

        this.nodeControlService.nodeExit(roleId, str2);
    }
}

package com.s4game.server.public_.nodecontrol.moduleinit;

import org.springframework.stereotype.Component;

import com.s4game.server.public_.nodecontrol.NodeControlModuleInfo;
import com.s4game.server.public_.nodecontrol.command.NodeControlCommands;
import com.s4game.server.public_.share.PublicModuleInit;
import com.s4game.server.share.event.EventHandleCommands;
import com.s4game.server.share.moduleinit.Group;
import com.s4game.server.share.moduleinit.ModuleInit;

@Component
public class NodeControlModuleInit extends PublicModuleInit {

    protected ModuleInit.InCmd getInCmd() {
        return new InCmd(NodeControlModuleInfo.MODULE_NAME, Group.PUBLIC.name,
                new String[] { NodeControlCommands.ROLE_IN, NodeControlCommands.ROLE_OUT });
    }

    @Override
    public EventHandleCommands getEventHandleCommands() {
        return null;
    }
}

package com.s4game.server.bus.init.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.core.message.Message;
import com.s4game.server.bus.init.command.InitCommands;
import com.s4game.server.bus.init.service.InitService;

@ActionWorker
public class InitAction {

    @Autowired
    private InitService ioService;

    @ActionMapping(mapping = InitCommands.ROLE_IN)
    public void roleIn(Message message) {
        String ip = (String) message.getData();

        ioService.roleIn(message.getRoleId(), ip);
    }

    @ActionMapping(mapping = InitCommands.ROLE_OUT)
    public void roleOut(Message message) {
        ioService.roleOut(message.getRoleId());
    }
}

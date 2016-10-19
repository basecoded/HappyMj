package com.s4game.server.gs.swap;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.s4game.core.message.Message;
import com.s4game.core.message.Message.DestType;
import com.s4game.core.message.Message.FromType;
import com.s4game.server.message.IMsgDispatcher;
import com.s4game.server.message.manager.SwapManager;
import com.s4game.server.share.moduleinit.CommandRegister;
import com.s4game.server.share.moduleinit.Group;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年9月11日 上午10:35:16
 * 
 */
@Component(value = "gsDispatcher")
public class GsMsgDispatcher implements IMsgDispatcher {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    @Resource
    private IMsgDispatcher busDispatcher;

    @Resource
    private IMsgDispatcher stageDispatcher;

    @Resource
    private SwapManager swapManager;

    @Override
    public void in(Message message) {
        FromType from = message.getFrom();

        switch (from) {
        case CLIENT:
            clientMsgSwap(message);
            break;
        default:
            componentMsgSwap(message);
        }
    }

    private void clientMsgSwap(Message message) {
        String command = message.getCommand();

        Group group = CommandRegister.getCmdGroup(command);

        message.setRoute(group.value);

        switch (group) {
        case BUS:
        case STAGE_CONTROL:
            this.busDispatcher.in(message);
            break;
        case STAGE:
            this.stageDispatcher.in(message);
            break;
        default:
            LOG.error("message cmd: {} not find group.", command);
        }
    }

    private void componentMsgSwap(Message message) {
        DestType dest = message.getDest();

        switch (dest) {
        case CLIENT:
            swapManager.swap(message);
            break;
        case BUS:
        case STAGE_CONTROL:
        case BUS_INIT:
            this.busDispatcher.in(message);
            break;
        case STAGE:
            this.stageDispatcher.in(message);
            break;
            
        case INOUT:
        case PUBLIC:
        case INNER_SYSTEM:
        default:
            LOG.error("message cmd: {} not find.", message.getCommand());
        }
    }
}

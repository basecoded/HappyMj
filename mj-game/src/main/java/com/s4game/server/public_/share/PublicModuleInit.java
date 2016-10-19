package com.s4game.server.public_.share;

import java.util.List;

import javax.annotation.Resource;

import com.s4game.core.event.IEventService;
import com.s4game.server.public_.event.PublicEventHandler;
import com.s4game.server.public_.swap.PublicMsgSender;
import com.s4game.server.share.event.EventHandleCommands;
import com.s4game.server.share.event.EventHandleCommands.Node;
import com.s4game.server.share.export.IEntityCacheLoaderExportService;
import com.s4game.server.share.moduleinit.ModuleInit;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月7日 下午6:36:09
 * 
 */

public abstract class PublicModuleInit extends ModuleInit {

    @Resource
    private IEventService eventService;
    
    @Resource
    private PublicMsgSender publicMsgSender;

    @Resource
    private IEntityCacheLoaderExportService publicCacheLoaderExportService;

    public void init() {
        super.init();

        if (null != getEventHandleCommands()) {
            List<Node> nodeList = getEventHandleCommands().nodes();
            for (Node node : nodeList) {
                this.eventService.subscribe(node.getEventType(), getOrder(), new PublicEventHandler(publicMsgSender, node.getEventType(), node.getCommand()));
            }
        }
    }

    @Override
    public IEntityCacheLoaderExportService getEntityCacheLoaderExportService() {
        return publicCacheLoaderExportService;
    }

    public abstract EventHandleCommands getEventHandleCommands();

    @Override
    public IEventService getEventService() {
        return eventService;
    }

}

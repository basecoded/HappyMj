package com.s4game.server.stage.share.moduleinit;

import java.util.List;

import javax.annotation.Resource;

import com.s4game.core.event.IEventService;
import com.s4game.server.bus.swap.BusMsgSender;
import com.s4game.server.share.event.EventHandleCommands;
import com.s4game.server.share.event.EventHandleCommands.Node;
import com.s4game.server.share.export.IEntityCacheLoaderExportService;
import com.s4game.server.share.moduleinit.ModuleInit;
import com.s4game.server.stage.share.event.StageEventCommandHandler;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月23日 下午9:20:33
 * 
 */
public abstract class AbsStageModuleInit extends ModuleInit {

	@Resource
	private IEventService eventService;
	
	@Resource
	private BusMsgSender busMsgSender;
	
	@Resource
	private IEntityCacheLoaderExportService busCacheLoaderExportService;

	public void init() {
		super.init();
		
		if (null != getEventHandleCommands()) {
			List<Node> nodeList = getEventHandleCommands().nodes();
			for (Node node : nodeList) {
				this.eventService.subscribe(node.getEventType(), getOrder(), new StageEventCommandHandler(busMsgSender, node.getCommand(), node.getEventType()));
			}
		}
	}

	@Override
	public IEntityCacheLoaderExportService getEntityCacheLoaderExportService() {
	    return busCacheLoaderExportService;
	}
	
	protected EventHandleCommands getEventHandleCommands() {
		return null;
	}
	
	@Override
	public IEventService getEventService() {
		return eventService;
	}

}

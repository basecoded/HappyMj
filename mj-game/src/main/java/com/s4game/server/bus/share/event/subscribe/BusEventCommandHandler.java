package com.s4game.server.bus.share.event.subscribe;

import com.s4game.core.event.IEventHandler;
import com.s4game.server.bus.swap.BusMsgSender;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月23日 下午9:22:52
 * 
 */
public class BusEventCommandHandler implements IEventHandler {

    private BusMsgSender busMsgSender;
    
	private String command;
	
	private String eventType;

	public BusEventCommandHandler(BusMsgSender busMsgSender, String command, String eventType) {
	    this.busMsgSender = busMsgSender;
		this.command = command;
		this.eventType = eventType;
	}
	
	@Override
	public void handle(Object source, Object data) {
	    busMsgSender.send2BusInner(command, (String)source, data);
	}

	@Override
	public String getEventType() {
		return eventType;
	}

}

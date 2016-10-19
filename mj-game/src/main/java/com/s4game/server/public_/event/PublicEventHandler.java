package com.s4game.server.public_.event;

import com.s4game.core.event.IEventHandler;
import com.s4game.server.public_.swap.PublicMsgSender;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月7日 下午6:32:54
 *
 */

public class PublicEventHandler implements IEventHandler {

    private PublicMsgSender msgSender;
    
	private String command;
	
	private String eventType;
	
	public PublicEventHandler(PublicMsgSender msgSender, String command, String eventType) {
	    this.msgSender = msgSender;
		this.command = command;
		this.eventType = eventType;
	}
	
	@Override
	public void handle(Object source, Object data) {
	    msgSender.send2PublicInner(command, (String)source, data);
	}

	@Override
	public String getEventType() {
		return eventType;
	}

}

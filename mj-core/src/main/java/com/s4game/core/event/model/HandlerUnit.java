package com.s4game.core.event.model;

import com.s4game.core.event.IEventHandler;

/**
 * 
 * 事件处理单元
 *  
 * @Author zeusgooogle@gmail.com
 * @sine 2015年4月23日 下午5:20:30
 * 
 */
public class HandlerUnit {

	private IEventHandler eventHandler;

	private int handleOrder;

	public HandlerUnit(IEventHandler eventHandler, int order) {
		this.handleOrder = order;
		this.eventHandler = eventHandler;
	}

	IEventHandler getEventHandler() {
		return this.eventHandler;
	}

	int getHandleOrder() {
		return this.handleOrder;
	}

}

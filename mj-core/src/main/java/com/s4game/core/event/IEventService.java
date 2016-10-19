package com.s4game.core.event;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年4月23日 下午5:41:13
 *
 */

public interface IEventService {

	/**
	 * 订阅事件
	 * 
	 * @param eventType
	 * @param order
	 * @param eventHandler
	 */
	public void subscribe(String type, int order, IEventHandler eventHandler);
	
	/**
	 * 发布事件
	 * @param event
	 */
	public void publish(IEvent event);
	
}

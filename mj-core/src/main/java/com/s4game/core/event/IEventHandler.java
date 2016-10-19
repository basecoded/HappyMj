package com.s4game.core.event;

/**
 * 
 * 事件处理
 * 
 * @Author zeusgooogle@gmail.com
 * @sine   2015年4月23日 下午5:08:56
 *
 */

public interface IEventHandler {

	/**
	 * @param source 事件源
	 * @param data   事件携带数据
	 */
	public void handle(Object source, Object data);
	
	/**
	 * <p>{@link See com.simplegame.event.IEvent}
	 * @return
	 */
	public String getEventType();
	
}

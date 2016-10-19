package com.s4game.core.event.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4game.core.event.IEvent;
import com.s4game.core.event.IEventHandler;

/**
 * 
 * 事件类型
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年4月23日 下午5:03:48
 * 
 */
public class EventType {

    private Logger LOG = LoggerFactory.getLogger(getClass());

	private String eventType;

	/**
	 * 一个事件有多人订阅
	 */
	private List<HandlerUnit> eventHandlers = new ArrayList<HandlerUnit>();

	public EventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * 订阅事件
	 * 
	 * @param order 事件响应时的排序优先级
	 * @param eventHandler
	 */
	public void subscribe(int order, IEventHandler eventHandler) {
		synchronized (this) {
			this.eventHandlers.add(new HandlerUnit(eventHandler, order));
			
			Collections.sort(this.eventHandlers, new Comparator<HandlerUnit>() {
				public int compare(HandlerUnit unit1, HandlerUnit unit2) {
					if (unit2.getHandleOrder() < unit1.getHandleOrder()) {
						return 1;
					}
					if (unit2.getHandleOrder() >= unit1.getHandleOrder()) {
						return -1;
					}
					return 0;
				}
			});
		}
	}

	public void publish(IEvent event) {
		synchronized (this) {
			Iterator<HandlerUnit> iterator = this.eventHandlers.iterator();
			while (iterator.hasNext()) {
				try {
					IEventHandler eventHandler = iterator.next().getEventHandler();
					eventHandler.handle(event.getSource(), event.getData());

				} catch (Exception exception) {
					LOG.error("eventType:{}, handler:{}, handle error: {}", this.eventType, IEventHandler.class.getName(), exception.getMessage());
				}
			}
		}
	}
}
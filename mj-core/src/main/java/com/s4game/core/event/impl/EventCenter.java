package com.s4game.core.event.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.s4game.core.event.IEvent;
import com.s4game.core.event.IEventHandler;
import com.s4game.core.event.model.EventType;

/**
 * 
 * 事件中心
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年4月23日 下午5:03:48
 * 
 */
@Component
public class EventCenter {
	
	/**
	 * key  : eventType
	 * value: EventType
	 */
	private Map<String, EventType> eventTypeMap = new HashMap<String, EventType>();

	public void subscribe(String type, int order, IEventHandler eventHandler) {
		synchronized (this.eventTypeMap) {
			EventType eventType = this.eventTypeMap.get(type);
			if (null == eventType) {
				eventType = new EventType(type);
				this.eventTypeMap.put(type, eventType);
			}
			eventType.subscribe(order, eventHandler);
		}
	}

	public void publish(IEvent event) {
		EventType eventType = this.eventTypeMap.get(event.getType());
		if (null != eventType) {
			eventType.publish(event);
		}
	}
}
package com.s4game.core.event;

/**
 *
 * 事件接口
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年4月23日 下午5:03:48
 *
 */

public interface IEvent {

	/**
	 * 获取事件类型
	 * @return
	 */
	public String getType();
	
	/**
	 * 获取事件携带的数据
	 * @return
	 */
	public Object getData();
	
	/**
	 * 获取事件源
	 * @return
	 */
	public Object getSource();
	
}

package com.s4game.core.container;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;

/**
 * 数据容器
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月2日 上午8:41:11
 * 
 */
@Component
public class DataContainer {

	private ConcurrentMap<String, ConcurrentMap<String, Object>> componentsMap = new ConcurrentHashMap<String, ConcurrentMap<String, Object>>();

	public DataContainer() {
	}

	public <T> void putData(String component, String k, T v) {
		getComponentDataMap(component).put(k, v);
	}

	public void removeData(String component, String k) {
		getComponentDataMap(component).remove(k);
	}

	@SuppressWarnings("unchecked")
    public <T> T getData(String component, String k) {
		Object object = getComponentDataMap(component).get(k);
		if (null != object) {
			return (T) object;
		}
		return null;
	}

	/**
	 * 获取模块数据，但是不能进行修改
	 * 
	 * @param componment
	 * @return
	 */
	public ConcurrentMap<String, Object> getDatas(String componment) {
		ConcurrentMap<String, Object> data = getComponentDataMap(componment);
		if (null != data) {
			return (ConcurrentMap<String, Object>) Collections.unmodifiableMap(data);
		}
		return null;
	}

	public ConcurrentMap<String, Object> remove(String componment) {
		return this.componentsMap.remove(componment);
	}

	private ConcurrentMap<String, Object> getComponentDataMap(String componment) {
		ConcurrentMap<String, Object> data = this.componentsMap.get(componment);
		if (null == data) {
			synchronized (this) {
				data = this.componentsMap.get(componment);
				if (null == data) {
					data = new ConcurrentHashMap<String, Object>();
					this.componentsMap.put(componment, data);
				}
			}
		}
		return data;
	}

}

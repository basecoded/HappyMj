package com.s4game.core.data.accessor.dao;

import com.s4game.core.data.IEntity;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年5月21日 下午5:05:35
 * 
 */

public interface IAdvancedDaoOperation<T extends IEntity> {

	public T load(String key, Object param, String accessType);

	public Object insert(T t, String key, String accessType);

	public int update(T t, String paramString1, String accessType);

	public int delete(Object paramObject, String paramString1, String accessType);

}

package com.s4game.core.data;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月20日 下午6:27:51
 *
 */

public interface IQueryFilter<T extends IEntity> {

	public boolean check(T t);

    public boolean stopped();
	
}

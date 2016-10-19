package com.s4game.core.data.accessor;

import java.util.List;

import com.s4game.core.data.IEntity;
import com.s4game.core.data.IQueryFilter;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月20日 下午6:26:43
 *
 */

public interface IDbAccessor {

    public Object insert(String key, IEntity entity, Class<? extends IEntity> clazz);
    
    public IEntity query(String key, Object param, Class<? extends IEntity> clazz);
    
    public List<IEntity> queryList(String key, Object param, Class<? extends IEntity> clazz);
    
    public List<IEntity> queryList(String key, Object param, IQueryFilter<IEntity> queryFilter, Class<? extends IEntity> clazz);
    
    public int delete(String key, Object param, Class<? extends IEntity> clazz);
    
    public int update(String key, IEntity entity );


	
}

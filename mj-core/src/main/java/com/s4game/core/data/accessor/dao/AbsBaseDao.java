package com.s4game.core.data.accessor.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.s4game.core.data.IEntity;
import com.s4game.core.data.accessor.AccessType;
import com.s4game.core.data.accessor.AccessorManager;
import com.s4game.core.data.accessor.GlobalIdentity;
import com.s4game.core.data.accessor.IDbAccessor;
import com.s4game.core.data.accessor.impl.MyBatisDatabaseAccessor;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月21日 下午5:08:04
 *
 */
public abstract class AbsBaseDao<T extends IEntity> implements IDaoContext, IParamDaoOperation<T>, IAdvancedDaoOperation<T> {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Class<T> poClass = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Autowired
    private AccessorManager accessorManager;

    public AbsBaseDao() {
    }

    @SuppressWarnings("unchecked")
    public T load(String key, Object param, String accessType) {
        IDbAccessor dbAccessor = this.accessorManager.getAccessor(accessType);
        return (T)dbAccessor.query(key, param, getEntityClass());
    }

    @Override
    public Object insert(T t, String key, String accessType) {
        IDbAccessor dbAccessor = this.accessorManager.getAccessor(accessType);
        return dbAccessor.insert(key, t, this.poClass);
    }

    @Override
    public int update(T t, String key, String accessType) {
        return this.accessorManager.getAccessor(accessType).update(key, t);
    }

    @Override
    public int delete(Object param, String key, String accessType) {
        return this.accessorManager.getAccessor(accessType).delete(key, param, getEntityClass());
    }
    
    @Override
    public List<T> getRecords(Map<String, Object> param) {
		return getRecords(param, GlobalIdentity.get(), getAccessType());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getRecords(Map<String, Object> param, String key, String accessType) {
        return (List<T>) ((MyBatisDatabaseAccessor) this.accessorManager.getAccessor(accessType)).queryList(key, param, getEntityClass());
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List query(String statement, Map<String, Object> param) {
    	MyBatisDatabaseAccessor dbAccessor = (MyBatisDatabaseAccessor)this.accessorManager.getAccessor(AccessType.getDirectDbType());
        return dbAccessor.customQuery(statement, param);
    }

    @Override
    public Object queryOne(String statement, Map<String, Object> param) {
    	MyBatisDatabaseAccessor dbAccessor = (MyBatisDatabaseAccessor)this.accessorManager.getAccessor(AccessType.getDirectDbType());
        return dbAccessor.customQueryOne(statement, param);
    }

    @Override
    public void update(String statement, Map<String, Object> param) {
    	MyBatisDatabaseAccessor dbAccessor = (MyBatisDatabaseAccessor)this.accessorManager.getAccessor(AccessType.getDirectDbType());
    	dbAccessor.customUpdate(statement, param);
    }

    public int getRecordsCount(Map<String, Object> param) {
    	MyBatisDatabaseAccessor dbAccessor = (MyBatisDatabaseAccessor)this.accessorManager.getAccessor(AccessType.getDirectDbType());
    	return dbAccessor.queryCount(param, getEntityClass());
    }
    
    public String getAccessType() {
        return this.accessorManager.getDefaultAccessType();
    }

    protected AccessorManager getAccessorManager() {
        return this.accessorManager;
    }

    public Class<? extends IEntity> getEntityClass() {
        return this.poClass;
    }
	
}

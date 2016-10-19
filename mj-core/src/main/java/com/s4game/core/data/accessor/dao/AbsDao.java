package com.s4game.core.data.accessor.dao;

import java.util.List;
import java.util.Map;

import com.s4game.core.data.IEntity;
import com.s4game.core.data.accessor.AccessType;

/**
 * @author zeusgooogle
 * @date 2014-10-1 下午09:26:20
 */

public abstract class AbsDao<T extends IEntity> extends AbsBaseDao<T> implements IDaoContext, IParamDaoOperation<T>, IAdvancedDaoOperation<T> {

    public AbsDao() {
    }

    public T dbLoad(Object param) {
        return load(null, param, AccessType.getDirectDbType());
    }

    public List<T> dbLoadAll() {
        return getRecords(null, null, AccessType.getDirectDbType());
    }

    public List<T> dbLoadAll(Map<String, Object> param) {
        return getRecords(param, null, AccessType.getDirectDbType());
    }

    public Object dbInsert(T t) {
        return insert(t, null, AccessType.getDirectDbType());
    }

    public int dbUpdate(T t) {
        return update(t, null, AccessType.getDirectDbType());
    }

    public int dbDelete(Object param) {
        return delete(param, null, AccessType.getDirectDbType());
    }

    public int dbLoadCount(Map<String, Object> param) {
        return getRecordsCount(param);
    }

}

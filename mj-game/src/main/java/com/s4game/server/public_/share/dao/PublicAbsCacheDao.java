package com.s4game.server.public_.share.dao;

import com.s4game.core.data.IEntity;
import com.s4game.core.data.accessor.AccessType;
import com.s4game.core.data.accessor.dao.AbsCacheDao;
import com.s4game.core.data.accessor.dao.ICacheInitDaoOperation;

public abstract class PublicAbsCacheDao<P extends IEntity> extends AbsCacheDao<P> implements ICacheInitDaoOperation<P> {
    
    public String getAccessType() {
        return AccessType.getPublicCacheDbType();
    }
}

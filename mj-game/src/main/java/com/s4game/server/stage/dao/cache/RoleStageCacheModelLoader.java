package com.s4game.server.stage.dao.cache;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.IEntityCache;
import com.s4game.core.data.accessor.cache.IEntityCacheModelLoader;
import com.s4game.server.stage.dao.IRoleStageDao;
import com.s4game.server.stage.entity.RoleStage;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月17日 下午3:33:30
 *
 */
@Component
public class RoleStageCacheModelLoader implements IEntityCacheModelLoader {

    @Resource
    private IRoleStageDao roleStageDao;
    
    @Override
    public void load(String id, IEntityCache entityCache) {
        entityCache.addData(roleStageDao.loadCacheFromDb(id), RoleStage.class);
    }

}

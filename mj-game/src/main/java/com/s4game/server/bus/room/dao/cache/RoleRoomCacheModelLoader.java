package com.s4game.server.bus.room.dao.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.IEntityCache;
import com.s4game.core.data.accessor.cache.IEntityCacheModelLoader;
import com.s4game.server.bus.room.dao.IRoleRoomDao;
import com.s4game.server.bus.room.entity.RoleRoom;

/**
* @Author zeusgooogle@gmail.com
* @sine   2016年9月17日 下午12:59:43 
*
*/
@Component
public class RoleRoomCacheModelLoader implements IEntityCacheModelLoader {

    @Autowired
    private IRoleRoomDao roleRoomDao;
    
    @Override
    public void load(String id, IEntityCache entityCache) {
        entityCache.addData(roleRoomDao.loadRoomFromDb(id), RoleRoom.class);
    }

}

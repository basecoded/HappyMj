package com.s4game.server.bus.room.dao.impl;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.AccessType;
import com.s4game.server.bus.room.dao.IRoleRoomDao;
import com.s4game.server.bus.room.entity.RoleRoom;
import com.s4game.server.bus.share.dao.BusAbsCacheDao;

/**
* @Author zeusgooogle@gmail.com
* @sine   2016年9月17日 下午12:47:13 
*
*/
@Component
public class RoleRoomDaoImpl extends BusAbsCacheDao<RoleRoom> implements IRoleRoomDao {

    @Override
    public RoleRoom loadRoomFromDb(String roleId) {
        return load(roleId, roleId, AccessType.getDirectDbType());
    }

    @Override
    public boolean updateDb(RoleRoom room) {
        return update(room, room.getUserRoleId(), AccessType.getDirectDbType()) > 0;
    }

    @Override
    public boolean deleteDb(RoleRoom room) {
        return delete(room.getUserRoleId(), room.getUserRoleId(), AccessType.getDirectDbType()) > 0;
    }

    @Override
    public RoleRoom insertDb(RoleRoom room) {
        return (RoleRoom) insert(room, room.getUserRoleId(), AccessType.getDirectDbType());
    }

}

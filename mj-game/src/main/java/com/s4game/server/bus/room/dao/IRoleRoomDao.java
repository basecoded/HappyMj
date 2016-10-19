package com.s4game.server.bus.room.dao;

import com.s4game.core.data.accessor.dao.ICacheInitDaoOperation;
import com.s4game.server.bus.room.entity.RoleRoom;

/**
* @Author zeusgooogle@gmail.com
* @sine   2016年9月17日 下午12:41:39 
*
*/
public interface IRoleRoomDao extends ICacheInitDaoOperation<RoleRoom> {

    RoleRoom loadRoomFromDb(String roleId);
    
    RoleRoom insertDb(RoleRoom room);
    
    boolean updateDb(RoleRoom room);
    
    boolean deleteDb(RoleRoom room);
}

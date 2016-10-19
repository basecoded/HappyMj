package com.s4game.server.bus.room.moduleinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.IEntityCacheModelLoader;
import com.s4game.core.event.IEventHandler;
import com.s4game.server.bus.room.RoleRoomModuleInfo;
import com.s4game.server.bus.room.dao.cache.RoleRoomCacheModelLoader;
import com.s4game.server.bus.room.event.subscribe.RoleLoginEventHandler;
import com.s4game.server.bus.share.moduleinit.BusModuleInit;
import com.s4game.server.share.moduleinit.Group;

/**
 * @Author zeusgooogle@gmail.com
 * @sine 2016年9月17日 下午12:08:58
 *
 */
@Component
public class RoleRoomModuleInit extends BusModuleInit {

    @Autowired
    private RoleRoomCacheModelLoader roleRoomCacheModelLoader;

    @Autowired
    private RoleLoginEventHandler roleLoginEventHandler;

    @Override
    protected InCmd getInCmd() {
        return new InCmd(RoleRoomModuleInfo.MODULE_NAME, Group.BUS.name,
                new String[] { });
    }

    protected ModuleInfo getModuleInfo() {
        return new ModuleInfo(RoleRoomModuleInfo.MODULE_NAME, RoleRoomModuleInfo.MODULE_NAME_ALIAS);
    }

    protected IEntityCacheModelLoader[] getEntityCacheModelLoaders() {
        return new IEntityCacheModelLoader[] { roleRoomCacheModelLoader };
    }

    protected IEventHandler[] getEventHandlers() {
        return new IEventHandler[] { roleLoginEventHandler };
    }

}

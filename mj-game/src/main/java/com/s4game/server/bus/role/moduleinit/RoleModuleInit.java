package com.s4game.server.bus.role.moduleinit;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.IEntityCacheModelLoader;
import com.s4game.server.bus.role.RoleModuleInfo;
import com.s4game.server.bus.role.dao.cache.RoleCacheModelLoader;
import com.s4game.server.bus.share.moduleinit.BusModuleInit;
import com.s4game.server.share.moduleinit.Group;

import static com.s4game.server.bus.role.command.RoleCommands.*;

/**
 * 
 * @Author zeusgooogle@gmail.com
 * @sine 2015年6月4日 下午4:50:39
 * 
 */
@Component
public class RoleModuleInit extends BusModuleInit {

	@Resource
	private RoleCacheModelLoader roleCacheModelLoader;
	
	protected IEntityCacheModelLoader[] getEntityCacheModelLoaders() {
		return new IEntityCacheModelLoader[] { this.roleCacheModelLoader };
	}
	
	@Override
	protected InCmd getInCmd() {
		return new InCmd(RoleModuleInfo.MODULE_NAME, Group.BUS.name, new String[] { ROLE_IN, ROLE_EXIT });
	}

	protected ModuleInfo getModuleInfo() {
		return new ModuleInfo(RoleModuleInfo.MODULE_NAME, RoleModuleInfo.MODULE_NAME_ALIAS);
	}
}

package com.s4game.server.bus.id.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.AccessType;
import com.s4game.core.data.accessor.GlobalIdentity;
import com.s4game.server.bus.id.dao.IIdGenDao;
import com.s4game.server.bus.id.dao.impl.IdGenDaoImpl;
import com.s4game.server.bus.id.entity.IdGen;
import com.s4game.server.bus.id.service.IIdGenService;
import com.s4game.server.utils.GameConfigureUtil;

/**
 * @Author zeusgooogle@gmail.com
 * @sine   2015年5月25日 下午9:54:34 
 *
 */
@Component
public class IdGenServiceImpl implements IIdGenService {

	private Map<String, ModuleVersion> moduleVersions = new HashMap<String, ModuleVersion>();

	@Resource
	private IIdGenDao idGenDao;

	public IdGenDaoImpl getDao() {
		return (IdGenDaoImpl) this.idGenDao;
	}
	
	@Override
	public IdGen getIdGenByModule(String moduleName, String prefix) {
		int version = getModuleVersion(moduleName);
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("moduleName", moduleName);
		params.put("version", version);
		
		List<IdGen> list = getDao().getRecords(params, GlobalIdentity.get(), AccessType.getDirectDbType());
		IdGen idGen = null;
		if (null != list && !list.isEmpty()) {
			idGen = list.get(0);
		}
		
		if (null == idGen) {
			idGen = new IdGen();
			idGen.setId(UUID.randomUUID().toString());
			idGen.setModuleName(moduleName);
			idGen.setVersion(Integer.valueOf(version));
			idGen.setValue(Long.valueOf(0L));
			idGen.setPrefix(prefix);
			
			getDao().insert(idGen, GlobalIdentity.get(), AccessType.getDirectDbType());
		}
		
		params = new HashMap<String, Object>();
		params.put("id", idGen.getId());

		getDao().update("updateIncValue", params);

		return idGen;
	}

	@Override
	public String getServerId() {
		return GameConfigureUtil.getServerId();
	}
	
	private int getModuleVersion(String moduleName) {
		ModuleVersion moduleVersion = this.moduleVersions.get(moduleName);
		if (null == moduleVersion) {
			moduleVersion = new ModuleVersion();
			this.moduleVersions.put(moduleName, moduleVersion);
		}
		return moduleVersion.nextVersion();
	}

	
	private class ModuleVersion {
		private int value;

		private ModuleVersion() {
		}

		private int nextVersion() {
			return ++this.value;
		}
	}

}

package com.s4game.server.bus.id.export;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import com.s4game.server.bus.id.entity.IdGen;
import com.s4game.server.bus.id.service.IIdGenService;

@Component
public class IdGenerator {

	@Resource
	private IIdGenService idGenService;

	private Map<String, NodeModuleId> moduleIdMap = new HashMap<String, NodeModuleId>();

	public void init(String moduleName, String prefix) {
		synchronized (IdGenerator.class) {
			if (this.moduleIdMap.containsKey(moduleName)) {
				throw new DuplicateKeyException("duplicate moduleName :" + moduleName);
			}

			this.moduleIdMap.put(moduleName, new NodeModuleId(getServerPrefix(), this.idGenService.getIdGenByModule(moduleName, prefix)));
		}
	}

	public String getId4Module(String moduleName) {
		NodeModuleId nodeModuleId = this.moduleIdMap.get(moduleName);
		if (null == nodeModuleId) {
			throw new NullPointerException("moduleName :" + moduleName + " 未注册,请通过各自模块的ModuleInit来注册ModuleInfo");
		}
		return nodeModuleId.nextId();
	}

	public String getServerPrefix() {
		return idGenService.getServerId();
	}

	private class NodeModuleId {
		private AtomicInteger localVersion = new AtomicInteger(0);
		private String nodePrefix;
		private ThreadLocal<ThreadModuleId> moduleId = new ThreadLocal<ThreadModuleId>();

		public NodeModuleId(String serverId, IdGen idGen) {
			this.nodePrefix = (serverId + idGen.getPrefix() + idGen.getVersion() + "-" + idGen.getValue() + "-");
		}

		public String nextId() {
			ThreadModuleId threadModuleId = this.moduleId.get();
			if (null == threadModuleId) {
				threadModuleId = new ThreadModuleId(getThreadPrefix());
				this.moduleId.set(threadModuleId);
			}
			return threadModuleId.nextId();
		}

		private String getThreadPrefix() {
			return this.nodePrefix + this.localVersion.getAndIncrement() + "-";
		}
	}

	private class ThreadModuleId {
		private String threadPrefix;
		private long curValue = 0L;

		public ThreadModuleId(String threadPrefix) {
			this.threadPrefix = threadPrefix;
		}

		public String nextId() {
			return this.threadPrefix + ++this.curValue;
		}
	}
}
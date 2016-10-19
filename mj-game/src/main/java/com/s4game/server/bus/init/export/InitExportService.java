package com.s4game.server.bus.init.export;

public interface InitExportService {
	
	public void roleIn(String roleId, String ip);

	public void roleOut(String roleId);
	
}

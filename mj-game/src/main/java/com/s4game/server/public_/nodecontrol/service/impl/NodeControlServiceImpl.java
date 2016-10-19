package com.s4game.server.public_.nodecontrol.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.manager.CacheManager;
import com.s4game.core.event.IEventService;
import com.s4game.core.sync.annotation.Sync;
import com.s4game.server.bus.init.export.InitExportService;
import com.s4game.server.bus.role.service.IUserRoleService;
import com.s4game.server.public_.nodecontrol.event.publish.RoleLogoutEvent;
import com.s4game.server.public_.nodecontrol.service.INodeControlService;
import com.s4game.server.public_.share.service.IPublicRoleStateService;

@Component
public class NodeControlServiceImpl implements INodeControlService {
    
    private boolean usecache = true;
    
    @Autowired
    private IPublicRoleStateService publicRoleStateService;
    
    @Autowired
    private InitExportService initExportService;
    
    @Autowired
    private IEventService eventService;
    
    @Autowired
    private IUserRoleService roleService;
    
    @Autowired
    @Qualifier("publicCacheManager")
    private CacheManager publicCacheManager;

    public NodeControlServiceImpl() {
    }

    public void change2online(String roleId) {
        this.publicRoleStateService.change2PublicOnline(roleId);
    }

    public void change2offline(String roleId) {
        this.publicRoleStateService.change2PublicOffline(roleId);
    }

    public void nodeLogin(String roleId, String ip) {
        if (this.usecache) {
            this.publicCacheManager.activateRoleCache(roleId);
        }
        this.initExportService.roleIn(roleId, ip);
    }

    public void nodeExit(String roleId, String paramString2) {
        if (this.publicRoleStateService.isPublicOnline(roleId)) {
            this.eventService.publish(new RoleLogoutEvent(roleId, paramString2,
                    this.roleService.getRole(roleId).getOnlineTime()));
        }
        
        if (this.usecache) {
            try {
                this.publicCacheManager.freezeRoleCache(roleId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        try {
            this.initExportService.roleOut(roleId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        change2offline(roleId);
    }

    @Sync(component = "public", indexes = { 0 })
    public void nodeExitOnServerClose(String paramString) {
        nodeExit(paramString, null);
    }
}
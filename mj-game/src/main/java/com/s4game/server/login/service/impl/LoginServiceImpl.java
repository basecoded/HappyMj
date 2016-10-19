package com.s4game.server.login.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.server.bus.role.entity.UserRole;
import com.s4game.server.bus.role.service.IUserRoleService;
import com.s4game.server.bus.server.service.IServerInfoService;
import com.s4game.server.login.service.ILoginService;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine 2015年6月8日 下午3:45:26
 *
 */
@Component
public class LoginServiceImpl implements ILoginService {

    @Resource
    private IUserRoleService userRoleService;

    @Resource
    private IServerInfoService serverInfoService;

    @Override
    public UserRole in(String userId, String serverId, String name, String face, String timestamp, String sign) {
        // String serverTag = GameConfigureUtil.getServerId() + "-" +
        // GameConfigureUtil.getServerVersion();

        UserRole userRole = userRoleService.getRole(userId, serverId);
        if (userRole == null) {
            userRole = userRoleService.createRole(userId, serverId, name, face, "");
        }
        
        return userRole;
    }

}

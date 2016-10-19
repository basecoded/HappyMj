package com.s4game.server.bus.share.rule;

import javax.annotation.Resource;

import com.s4game.server.bus.share.service.IRoleStateService;
import com.s4game.server.executor.IRuleChecker;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年6月19日 下午6:10:28
 *
 */
public class BusRuleChecker implements IRuleChecker {

    @Resource
    private IRoleStateService roleStateService;
    
    @Override
    public boolean valid(Object roleId) {
        return roleStateService.isOnline((String)roleId);
    }

}

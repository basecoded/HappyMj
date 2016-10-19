package com.s4game.server.stage.share.rule;

import javax.annotation.Resource;

import com.s4game.server.executor.IRuleChecker;
import com.s4game.server.stage.service.IStageService;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月21日 下午3:04:26
 *
 */
public class StageRuleChecker implements IRuleChecker {

    @Resource
    private IStageService stageService;
    
    @Override
    public boolean valid(Object rule) {
        return stageService.exist((String)rule);
    }

}

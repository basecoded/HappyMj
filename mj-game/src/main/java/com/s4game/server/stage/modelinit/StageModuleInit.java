package com.s4game.server.stage.modelinit;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.s4game.core.data.accessor.cache.IEntityCacheModelLoader;
import com.s4game.core.event.IEventHandler;
import com.s4game.server.stage.StageModuleInfo;
import com.s4game.server.stage.command.StageCommands;
import com.s4game.server.stage.dao.cache.RoleStageCacheModelLoader;
import com.s4game.server.stage.event.subscribe.StageRoleCreateHandler;
import com.s4game.server.stage.event.subscribe.StageRoleLogoutHandler;
import com.s4game.server.stage.service.IStageService;
import com.s4game.server.stage.share.moduleinit.AbsStageModuleInit;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年7月17日 下午3:40:59
 *
 */
@Component
public class StageModuleInit extends AbsStageModuleInit {

    @Resource
    private RoleStageCacheModelLoader roleStageCacheModelLoader;
    
    @Resource
    private StageRoleCreateHandler stageRoleCreateHandler;
   
    @Resource
    private StageRoleLogoutHandler stageRoleLogoutHandler;
    
    @Resource
    private IStageService stageService;
    
    @Override
    protected InCmd getInCmd() {
        String[] cmd = new String[] {
                    StageCommands.INNER_LEAVE_STAGE,
                    StageCommands.INNER_ENTER_STAGE,
                    
                    //关卡
                    StageCommands.CHECKPOINT_EXPIRE_CHECK,
                    StageCommands.CHECKPOINT_FORCE_CHECK,
                    StageCommands.CHECKPOINT_CHELLENGE_RESULT,
                    StageCommands.CHECKPOINT_LEAVE
        };
        
        return new InCmd(StageModuleInfo.MODULE_NAME, StageModuleInfo.MODULE_NAME_ABBR, cmd);
    }

    protected IEntityCacheModelLoader[] getEntityCacheModelLoaders() {
        return new IEntityCacheModelLoader[] { this.roleStageCacheModelLoader };
    }
    
    protected IEventHandler[] getEventHandlers() {
        return new IEventHandler[] { this.stageRoleCreateHandler, this.stageRoleLogoutHandler };
    }
    
    
}

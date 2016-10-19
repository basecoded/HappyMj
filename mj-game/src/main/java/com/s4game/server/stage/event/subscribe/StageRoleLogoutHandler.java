package com.s4game.server.stage.event.subscribe;

import org.springframework.stereotype.Component;

import com.s4game.core.event.IEventHandler;
import com.s4game.server.share.event.EventConstants;

@Component
public class StageRoleLogoutHandler implements IEventHandler {
    
//    @Autowired
//    private IAutoFightService autoFightService;

    @Override
    public void handle(Object source, Object data) {
//        this.autoFightService.offlineHandler((String) paramObject1);
    }

    @Override
    public String getEventType() {
        return EventConstants.ROLE_LOGOUT;
    }
}
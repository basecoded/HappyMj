package com.s4game.server.bus.init.moduleinit;

import org.springframework.stereotype.Component;

import com.s4game.server.bus.init.InitMuduleInfo;
import com.s4game.server.bus.init.command.InitCommands;
import com.s4game.server.bus.share.moduleinit.BusModuleInit;
import com.s4game.server.share.moduleinit.Group;

/**
 *
 * @Author zeusgooogle@gmail.com
 * @sine   2015年6月30日 下午1:58:35
 *
 */
@Component
public class InitModuleInit extends BusModuleInit {

    @Override
    protected InCmd getInCmd() {
        return new InCmd(InitMuduleInfo.MODULE_NAME, Group.BUS.name, new String[] { InitCommands.ROLE_IN, InitCommands.ROLE_OUT });
    }

}

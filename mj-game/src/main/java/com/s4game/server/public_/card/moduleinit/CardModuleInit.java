package com.s4game.server.public_.card.moduleinit;

import org.springframework.stereotype.Component;	

import static com.s4game.server.public_.card.command.CardCommands.*;

import com.s4game.server.public_.card.CardModuleInfo;
import com.s4game.server.public_.share.PublicModuleInit;
import com.s4game.server.share.event.EventHandleCommands;
import com.s4game.server.share.moduleinit.Group;

/**
*
* @author zeusgoogogle@gmail.com
* @sine 2016年9月24日 下午8:30:32
*
*/
@Component
public class CardModuleInit extends PublicModuleInit {

	@Override
	public EventHandleCommands getEventHandleCommands() {
		return null;
	}

	@Override
	protected InCmd getInCmd() {
	    String[] cmd = new String[]{CARD_INIT, CARD_PLAY, CARD_ACTION};
	    
		return new InCmd(CardModuleInfo.MODULE_NAME, Group.PUBLIC.name, cmd);
	}

}

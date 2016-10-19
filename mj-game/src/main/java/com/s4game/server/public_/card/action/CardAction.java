package com.s4game.server.public_.card.action;

import org.slf4j.Logger;

import com.s4game.core.action.annotation.ActionMapping;
import com.s4game.core.action.annotation.ActionWorker;
import com.s4game.core.message.Message;
import com.s4game.server.public_.card.command.CardCommands;
import com.s4game.server.share.log.Log;

/**
*
* @author zeusgoogogle@gmail.com
* @sine 2016年9月24日 下午8:33:10
*
*/
@ActionWorker
public class CardAction {
	
	public static final Logger LOG = Log.CARD;

	@ActionMapping(mapping = CardCommands.CARD_PLAY)
	public void play(Message message) {
		
	}
	
}

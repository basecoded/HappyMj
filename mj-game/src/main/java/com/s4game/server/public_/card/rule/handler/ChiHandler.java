package com.s4game.server.public_.card.rule.handler;

import java.util.List;

import com.s4game.server.public_.card.model.card.Card;
import com.s4game.server.public_.card.model.card.ActionType;
import com.s4game.server.public_.card.model.group.ICardGroup;

public class ChiHandler extends AbstractCardHandler {

    @Override
    public void handler(List<Card> handCards, List<ICardGroup> groups) {
    }

    @Override
    public boolean match(List<ICardGroup> groups, Card card) {
        ICardGroup handCardGroup = getHandGroup(groups);
        
        boolean match111 = match111(handCardGroup.getCards(), card);
        boolean match123 = match123(handCardGroup.getCards(), card);
        boolean match2710 = match2710(handCardGroup.getCards(), card);
        
        return match111 || match123 || match2710;
    }

    @Override
    public ActionType getBehavior() {
        return ActionType.CHI;
    }

}

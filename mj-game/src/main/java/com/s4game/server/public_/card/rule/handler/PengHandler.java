package com.s4game.server.public_.card.rule.handler;

import java.util.List;
import java.util.Map;

import com.s4game.server.public_.card.model.card.Card;
import com.s4game.server.public_.card.model.card.ActionType;
import com.s4game.server.public_.card.model.group.ICardGroup;

public class PengHandler extends AbstractCardHandler {

    @Override
    public void handler(List<Card> handCards, List<ICardGroup> groups) {
    }

    @Override
    public boolean match(List<ICardGroup> groups, Card card) {
        ICardGroup handCardGroup = getHandGroup(groups);
        
        List<Card> handCards = handCardGroup.getCards();
        Map<String, List<Card>> map = groupBy(handCards);
        
        for (List<Card> cards : map.values()) {
            Card orignalCard = cards.get(0);
            if (cards.size() == 2 && orignalCard.isSame(card)) {
                return true;
            }
        }
        
        return false;
    }

    @Override
    public ActionType getBehavior() {
        return ActionType.PENG;
    }

}

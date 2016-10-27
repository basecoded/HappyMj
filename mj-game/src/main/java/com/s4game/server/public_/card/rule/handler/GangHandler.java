package com.s4game.server.public_.card.rule.handler;

import java.util.List;
import java.util.Map;

import com.s4game.server.public_.card.model.card.ActionType;
import com.s4game.server.public_.card.model.card.Card;
import com.s4game.server.public_.card.model.group.CardGroupType;
import com.s4game.server.public_.card.model.group.DefaultCardGroup;
import com.s4game.server.public_.card.model.group.ICardGroup;

public class GangHandler extends AbstractCardHandler {

    @Override
    public void handler(List<Card> handCards, List<ICardGroup> groups) {
        Map<String, List<Card>> map = groupBy(handCards);
        
        for (List<Card> cards : map.values()) {
            if (cards.size() == 4) {
                groups.add(new DefaultCardGroup(CardGroupType.GANG, cards));
                
                handCards.removeAll(cards);
            }
        }
    }
    
    @Override
    public boolean match(List<ICardGroup> groups, Card card) {
        for (ICardGroup group : groups) {
            if (group.getType() == CardGroupType.PENG) {
                Card originalCard = group.getCards().get(0);

                if (originalCard.isSame(card)) {
                    group.addCard(card);
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public ActionType getBehavior() {
        return ActionType.GANG;
    }

}

package com.s4game.server.public_.card.rule.handler;

import java.util.List;
import java.util.Map;

import com.s4game.server.public_.card.model.card.Card;
import com.s4game.server.public_.card.model.card.CardBehavior;
import com.s4game.server.public_.card.model.group.CardGroupType;
import com.s4game.server.public_.card.model.group.DefaultCardGroup;
import com.s4game.server.public_.card.model.group.ICardGroup;

public class KanHandler extends AbstractCardHandler {

    @Override
    public void handler(List<Card> handCards, List<ICardGroup> groups) {
        Map<String, List<Card>> map = groupBy(handCards);
        
        for (List<Card> cards : map.values()) {
            if (cards.size() == 3) {
                groups.add(new DefaultCardGroup(CardGroupType.KAN, cards));
                
                handCards.removeAll(cards);
            }
        }
    }
    
    @Override
    public boolean match(List<ICardGroup> groups, Card card) {
        return false;
    }

    @Override
    public CardBehavior getBehavior() {
        return CardBehavior.KAN;
    }

}

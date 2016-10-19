package com.s4game.server.public_.card.rule.handler;

import java.util.List;

import com.s4game.server.public_.card.model.card.Card;
import com.s4game.server.public_.card.model.card.CardBehavior;
import com.s4game.server.public_.card.model.group.ICardGroup;

public interface CardHandler {

    /**
     * 从手牌中匹配出牌组， 3张相同 = 坎， 4张相同 = 龙
     * 
     * @param cards
     * @param groups
     */
    void handler(List<Card> handCards, List<ICardGroup> groups);

    /**
     * 执行规则检查
     * 
     * @param groups
     * @param card
     * @return
     */
    boolean match(List<ICardGroup> groups, Card card);
    
    /**
     * match 成功后，可以进行的操作行为
     * 
     * @return
     */
    CardBehavior getBehavior();

}

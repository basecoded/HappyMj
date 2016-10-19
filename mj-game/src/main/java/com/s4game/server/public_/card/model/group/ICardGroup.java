package com.s4game.server.public_.card.model.group;

import java.util.List;

import com.s4game.server.public_.card.model.card.Card;

public interface ICardGroup {

    /**
     * 牌组类型
     * @return
     */
    CardGroupType getType();

    /**
     * 添加牌
     * @param cards
     */
    void addCard(Card card);
    
    
    /**
     * 获取牌列表
     * 
     * @return
     */
    List<Card> getCards();

    /**
     * 改变牌组类型
     * 比如：碰 -> 跑， 坎 -> 龙
     * 
     * @param type
     */
    void changeType(CardGroupType type);
    
    
}

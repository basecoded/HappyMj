package com.s4game.server.public_.card.rule.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.s4game.server.public_.card.model.card.Card;
import com.s4game.server.public_.card.model.card.CardType;
import com.s4game.server.public_.card.model.group.CardGroupType;
import com.s4game.server.public_.card.model.group.ICardGroup;

public abstract class AbstractCardHandler implements CardHandler {

    /**
     * 根据 Card.getIdentity() 分组
     * 
     * @param cards
     * @return
     */
    Map<String, List<Card>> groupBy(List<Card> cards) {
        Map<String, List<Card>> map = new HashMap<>();
        for (Card card : cards) {
            List<Card> tmp = map.get(card.getIdentity());
            if (tmp == null) {
                tmp = new ArrayList<>();
                tmp.add(card);
                
                map.put(card.getIdentity(), tmp);
            } else {
                tmp.add(card);
            }
        }
        
        return map;
    }
    
    ICardGroup getHandGroup(List<ICardGroup> groups) {
        ICardGroup handGroup = null;
        for (ICardGroup cg : groups) {
            if (cg.getType() == CardGroupType.HAND) {
                handGroup = cg;
                break;
            }
        }
        return handGroup;
    }
    
    /**
     * 匹配顺子
     * 
     * 去 N +- 2 位置的牌
     * 
     * @param cardMap
     */
    boolean match123(List<Card> cards, Card curData) {
        List<Card> tmp = new ArrayList<>();

        int serial = 0; // 连续两次
        int value = curData.getValue();
        for (int i = value - 2; i <= value + 2; i++) {
            if (i == value) {
                continue;
            }

            Card matchCard = findValue(cards, i, curData.getType());
            if (matchCard != null) {
                serial++;
                tmp.add(matchCard);

                if (serial >= 2) { // 匹配成功
                    break;
                }
            } else {
                serial = 0;
                tmp.clear();
            }
        }

        if (serial >= 2) {
            tmp.add(0, curData);
            for (Card d : tmp) {
                cards.remove(d);
            }
            return true;
        }
        return false;
    
    }

    /**
     * 匹配 2, 7, 10
     * 
     * @param cards
     */
    boolean match2710(List<Card> cards, Card curCard) {
        int[] values = new int[]{2, 7 ,10};
        List<Card> tmp = new ArrayList<>();
        tmp.add(curCard);
        
        for (int v : values) {
            Card card = findValue(cards, v, curCard.getType());
            if (card != null) {
                tmp.add(card);
            }
        }
        
        if (tmp.size() < values.length) {
            return false;
        } else {
            for (Card c : tmp) {
                cards.remove(c);
            }
            return true;
        }
    }
    
    /**
     * 匹配相同牌面值(大，小)，形成一句话
     * 
     * @param cards
     * @param curCard
     * @return
     */
    boolean match111(List<Card> cards, Card curCard) {
        List<Card> tmp = new ArrayList<>();
        
        int count = 0;
        for (Card d : cards) {
            if (count >= 3) {
                break;
            }
            
            if (d.getValue() == curCard.getValue()) {
                tmp.add(d);
                count++;
            }
        }
        
        if (tmp.size() >= 3) {
            for (Card c : tmp) {
                cards.remove(c);
            }
            return true;
        }
        return false;
    }
    
    Card findValue(List<Card> cards, int value, CardType type) {
        for (Card d : cards) {
            if (d.getValue() == value && d.getType() == type) {
                return d;
            }
        }
        return null;
    }
}

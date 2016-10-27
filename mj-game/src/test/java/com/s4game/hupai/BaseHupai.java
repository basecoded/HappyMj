package com.s4game.hupai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.s4game.core.tuple.Tuple;
import com.s4game.core.tuple.TwoTuple;
import com.s4game.server.public_.card.CardConstants;
import com.s4game.server.public_.card.model.card.Card;
import com.s4game.server.public_.card.model.card.CardState;
import com.s4game.server.public_.card.model.card.CardType;
import com.s4game.server.utils.id.IdUtil;

public abstract class BaseHupai {

    public final Logger LOG = LoggerFactory.getLogger(getClass());
    
    public static final String stageId = "0";
    
    public ArrayList<Card> initCards() {
        ArrayList<Card> cards = new ArrayList<>();

        for (CardType type : CardType.values()) {
            if (type.isNumeric()) {
                for (int value : CardConstants.CARD_VALUE) {
                    cards.add(new Card(nextCardId(stageId), value, type, CardState.BOTTOM_CARD));
                    cards.add(new Card(nextCardId(stageId), value, type, CardState.BOTTOM_CARD));
                    cards.add(new Card(nextCardId(stageId), value, type, CardState.BOTTOM_CARD));
                    cards.add(new Card(nextCardId(stageId), value, type, CardState.BOTTOM_CARD));
                }
            } else {
                cards.add(new Card(nextCardId(stageId), type.getId(), type, CardState.BOTTOM_CARD));
                cards.add(new Card(nextCardId(stageId), type.getId(), type, CardState.BOTTOM_CARD));
                cards.add(new Card(nextCardId(stageId), type.getId(), type, CardState.BOTTOM_CARD));
                cards.add(new Card(nextCardId(stageId), type.getId(), type, CardState.BOTTOM_CARD));
            }
        }

        Collections.shuffle(cards);

        return cards;
    }
    
    /**
     * 取任意一张牌，匹配顺子
     * 
     * 去 N +- 2 位置的牌
     * 
     * @param cardMap
     */
    public boolean match123(List<Card> cards, Card curData) {
        if (!curData.getType().isNumeric()) {
            return match111(cards, curData);
        }
        
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

            //LOG.info("match 123 success. cards: {}", tmp);
            
            for (Card d : tmp) {
                cards.remove(d);
            }
            return true;
        }
        
        return false;
    }
    
    public boolean match111(List<Card> cards, Card curCard) {
        List<Card> tmp = new ArrayList<>();
        //tmp.add(curCard);
        
        int count = 0;
        for (Card d : cards) {
            if (count >= 3) {
                break;
            }
            
            if (curCard.isSame(d)) {
                tmp.add(d);
                count++;
            }
        }
        
        if (tmp.size() >= 3) {
            //LOG.info("match 111 success. cards: {}", tmp);
            
            for (Card c : tmp) {
                cards.remove(c);
            }
            
            return true;
        }
        
        return false;
    }
    
    public boolean matchPair(List<Card> cards, TwoTuple<Card, Card> pair) {
        cards.remove(pair.getFirst());
        cards.remove(pair.getSecond());
        
        return true;
    }
    
    /**
     * 匹配相同牌 形成一句话
     * 
     * @param cards
     */
    public boolean canMatch111(List<Card> cards, Card source) {
        int count = 0;
        
        for (Card d : cards) {
            if (source.isSame(d)) {
                count++;
            }
        }
        
        return count == 3;
    }
    
    public boolean canMatch123(List<Card> cards, Card source) {
        if (!source.getType().isNumeric()) {
            return canMatch111(cards, source);
        }
        
        boolean matched = false;
        List<Card> tmp = new ArrayList<>();

        int serial = 0; // 连续两次
        int value = source.getValue();
        for (int i = value - 2; i <= value + 2; i++) {
            if (i == value) {
                continue;
            }

            Card matchCard = findValue(cards, i, source.getType());
            if (matchCard != null) {
                serial++;
                tmp.add(matchCard);

                if (serial >= 2) { // 匹配成功
                    matched = true;
                    break;
                }
            } else {
                serial = 0;
                tmp.clear();
            }
        }
        
        return matched;
    }
    
    /**
     * 匹配对子
     * 
     * @param cards
     * @param source
     * @return
     */
    public boolean canMatchPair(List<Card> cards, Card source) {
        int count = 0;
        
        for (Card d : cards) {
            if (source.isSame(d)) {
                count++;
            }
        }
        
        return count >= 2;
    }
    
    public Card findValue(List<Card> cards, int value) {
        for (Card d : cards) {
            if (d.getValue() == value) {
                return d;
            }
        }
        return null;
    }
    
    public Card findValue(List<Card> cards, int value, CardType type) {
        for (Card d : cards) {
            if (d.getValue() == value && d.getType() == type) {
                return d;
            }
        }
        return null;
    }
    
    /**
     * 查找 一对 的数量
     * 
     * @param cards
     * @return
     */
    public List<TwoTuple<Card, Card>> findPairs(List<Card> cards) {
        List<TwoTuple<Card, Card>> paris = new ArrayList<>();
        
        Map<String, Card> map = new HashMap<>();
        
        TwoTuple<Card, Card> tuple = null;
        for (Card d : cards) {
            if (map.containsKey(d.getIdentity())) {
                tuple = Tuple.tuple(map.get(d.getIdentity()), d);
                paris.add(tuple);
            }
            
            map.put(d.getIdentity(), d);
        }
        
        return paris;
    }
    
    public String nextCardId(String stageId) {
        return IdUtil.nextString(stageId);
    }
}

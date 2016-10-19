package com.s4game.server.public_.card.service;

import java.util.ArrayList;
import java.util.List;

import com.s4game.core.tuple.TwoTuple;
import com.s4game.server.public_.card.model.card.Card;
import com.s4game.server.public_.card.model.card.CardType;
import com.s4game.server.stage.room.RoomStage;

public interface ICardService {

    /**
     * 发牌
     * @param stage
     */
    void deal(RoomStage stage);
    
    /**
     * 玩家初始化牌以后，找出坎，龙
     */
    void preproccess(RoomStage stage);
    
    /**
     * 出牌
     * 
     * @param roleId
     * @param cardId
     */
    void play(String roleId, String cardId);
    
    /**
     * 抓牌
     * @param roleId
     */
    void draw(String roleId);
    
    /**
     * 检测是否胡牌
     * 
     * @param cards
     * @return
     */
    boolean hupai(ArrayList<Card> cards);
    
    /**
     * 初始化一副牌
     * 
     * @param stageId
     * @return
     */
    List<Card> initCards(String stageId);
    
    
    /**
     * 匹配顺子
     * 
     * 去 N +- 2 位置的牌
     * 
     * @param cardMap
     */
    boolean match123(List<Card> cards, Card curData);

    /**
     * 匹配 2, 7, 10
     * 
     * @param cards
     */
    boolean match2710(List<Card> cards, Card curCard);
    
    /**
     * 匹配相同牌面值(大，小)，形成一句话
     * 
     * @param cards
     * @param curCard
     * @return
     */
    boolean match111(List<Card> cards, Card curCard);
    
    /**
     * 匹配对子
     * 
     * @param cards
     * @param pair
     * @return
     */
    boolean matchPair(List<Card> cards, TwoTuple<Card, Card> pair);
    
    /**
     * 匹配相同牌面值(大，小)，形成一句话
     * 
     * @param cards
     */
    boolean canMatch111(List<Card> cards, Card source);
    
    /**
     * 匹配顺子
     * 
     * @param cardMap
     */
    boolean canMatch123(List<Card> cards, Card source);
    
    /**
     * 匹配 2, 7, 10
     * 
     * @param cards
     */
    boolean canMatch2710(List<Card> cards, Card source);
    
    /**
     * 匹配对子
     * 
     * @param cards
     * @param source
     * @return
     */
    boolean canMatchPair(List<Card> cards, Card source);
    
    Card findValue(List<Card> cards, int value);
    
    Card findValue(List<Card> cards, int value, CardType type);
    
    /**
     * 查找 对子
     * 
     * @param cards
     * @return
     */
    List<TwoTuple<Card, Card>> findPairs(List<Card> cards);
}

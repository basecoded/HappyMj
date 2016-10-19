package com.s4game.server.public_.card.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s4game.core.container.DataContainer;
import com.s4game.core.tuple.Tuple;
import com.s4game.core.tuple.TwoTuple;
import com.s4game.server.bus.share.constants.BusShareConstant;
import com.s4game.server.bus.stagecontroll.RoleState;
import com.s4game.server.public_.card.model.card.Card;
import com.s4game.server.public_.card.model.card.CardState;
import com.s4game.server.public_.card.model.card.CardType;
import com.s4game.server.public_.card.rule.handler.CardHandler;
import com.s4game.server.public_.card.rule.handler.ChiHandler;
import com.s4game.server.public_.card.rule.handler.DefaultCardPipeline;
import com.s4game.server.public_.card.rule.handler.KanHandler;
import com.s4game.server.public_.card.rule.handler.LongHandler;
import com.s4game.server.public_.card.rule.handler.PengHandler;
import com.s4game.server.public_.card.service.ICardService;
import com.s4game.server.public_.room.RoomConstants;
import com.s4game.server.public_.room.model.RoomBusinessData;
import com.s4game.server.public_.room.model.RoomMemberData;
import com.s4game.server.share.log.Log;
import com.s4game.server.stage.room.RoomStage;
import com.s4game.server.stage.service.IStageService;
import com.s4game.server.utils.MathUtils;
import com.s4game.server.utils.id.IdUtil;

@Service
public class CardServiceImpl implements ICardService {

    public static final Logger LOG = Log.CARD;

    @Autowired
    private IStageService stageService;

    @Autowired
    private DataContainer dataContainer;

    private DefaultCardPipeline cardPipeline;
    
    @PostConstruct
    public void init() {
        cardPipeline = new DefaultCardPipeline();
        cardPipeline.addLast("long", new LongHandler())
                    .addLast("kan" , new KanHandler())
                    .addLast("peng", new PengHandler())
                    .addLast("chi", new ChiHandler());
    }
    
    @Override
    public void deal(RoomStage stage) {
        RoomBusinessData businessData = stage.getRoomBusinessData();

        List<Card> initCards = initCards(stage.getId());

        // 初始化牌
        businessData.setInitCards(initCards);

        // 随机庄家
        RoomMemberData dealer = randomDealer(businessData);

        // 发牌
        int count = 0;
        int index = 0;
        while (count < RoomConstants.INIT_CARD_SIZE) {
            RoomMemberData member = businessData.getMembers().get(index);

            Card card = initCards.remove(0);
            member.addHandCard(card);

            count++;
            index++;

            if (index % RoomConstants.MEMBER_SIZE == 0) {
                index = 0;
            }
        }

        // 庄家多抓一张牌
        Card lastCard = initCards.remove(0);
        dealer.addHandCard(lastCard);

        preproccess(stage);
    }

    @Override
    public void preproccess(RoomStage stage) {
        //庄家检查 龙，坎，胡牌；其他玩家检查龙，坎
        RoomBusinessData businessData = stage.getRoomBusinessData();
        
        for (RoomMemberData member : businessData.getMembers()) {
            handler0(member);
            
            if (member.isDealer()) {
                boolean hupai = hupai((ArrayList<Card>) member.getHandCard().getCards());
                if (hupai) {
                    LOG.info("tian hu. roleId: {}, name: {}", member.getRoleId(), member.getName());
                }
            }
        }
    }
    
    private void handler0(RoomMemberData member){
        for (Iterator<Map.Entry<String, CardHandler>> iterator = cardPipeline.iterator(); iterator.hasNext();) {
            CardHandler handler = iterator.next().getValue();
            
            handler.handler(member.getHandCard().getCards(), member.getGroups());
        }
    }
    
    /**
     * 1. 开始游戏，随机一个
     * 2. 一局打完，胡牌人庄 ，没人胡牌，随机一个
     * 
     * @param businessData
     */
    private RoomMemberData randomDealer(RoomBusinessData businessData) {
        List<RoomMemberData> members = businessData.getMembers();

        int index = MathUtils.random(0, members.size() - 1);
        RoomMemberData member = members.get(index);
        member.setDealer(true);

        // 设置庄家为出牌人
        businessData.setPlayCardIndex(index);
        
        return member;
    }

    /**
     * 创建一副牌
     * 
     * @return
     */
    @Override
    public List<Card> initCards(String stageId) {
        List<Card> cards = new ArrayList<>();

        for (int v : RoomConstants.CARD_VALUE) {
            cards.add(new Card(nextCardId(stageId), v, CardType.SMALL, CardState.BOTTOM_CARD));
            cards.add(new Card(nextCardId(stageId), v, CardType.SMALL, CardState.BOTTOM_CARD));
            cards.add(new Card(nextCardId(stageId), v, CardType.SMALL, CardState.BOTTOM_CARD));
            cards.add(new Card(nextCardId(stageId), v, CardType.SMALL, CardState.BOTTOM_CARD));
        }

        for (int v : RoomConstants.CARD_VALUE) {
            cards.add(new Card(nextCardId(stageId), v, CardType.BIG, CardState.BOTTOM_CARD));
            cards.add(new Card(nextCardId(stageId), v, CardType.BIG, CardState.BOTTOM_CARD));
            cards.add(new Card(nextCardId(stageId), v, CardType.BIG, CardState.BOTTOM_CARD));
            cards.add(new Card(nextCardId(stageId), v, CardType.BIG, CardState.BOTTOM_CARD));
        }

        Collections.shuffle(cards);

        return cards;
    }

    private String nextCardId(String stageId) {
        return IdUtil.nextString(stageId);
    }

    @Override
    public void play(String roleId, String cardId) {
        RoleState roleState = dataContainer.getData(BusShareConstant.COMPONENT_NAME, roleId);
        if (null == roleState) {
            return;
        }

        String stageId = roleState.getCurPosition().getStageId();
        RoomStage stage = stageService.getStage(stageId);
        if (null == stage) {
            LOG.error("role: {} can't play card. not in stage.", roleId);
            return;
        }
        
        

    }
    
    @Override
    public void draw(String roleId) {
        
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean hupai(ArrayList<Card> cards) {
        return match((ArrayList<Card>)cards.clone(), cards);
    }

    @SuppressWarnings("unchecked")
    private boolean match(ArrayList<Card> cards, List<Card> sourceCards) {
        if (cards.isEmpty()) {
            LOG.info("hupai. cards: {}", sourceCards);
            return true;
        }
        
        Card curCard = cards.get(0);
        boolean match111 = canMatch111(sourceCards, curCard);
        boolean match123 = canMatch123(sourceCards, curCard);
        boolean match2710 = canMatch2710(sourceCards, curCard);
        
        if (match111) {
            ArrayList<Card> copy = (ArrayList<Card>) cards.clone();
            if (match111(copy, curCard)) {
                match(copy, sourceCards);
            }
        }
        
        if (match123) {
            ArrayList<Card> copy = (ArrayList<Card>) cards.clone();
            if (match123(copy, curCard)) {
                match(copy, sourceCards);
            }
        }
        
        if (match2710) {
            ArrayList<Card> copy = (ArrayList<Card>) cards.clone();
            if (match2710(copy, curCard)) {
                match(copy, sourceCards);
            }
        }
        
        return false;
    }
    
    @Override
    public boolean match123(List<Card> cards, Card curData) {
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

    @Override
    public boolean match2710(List<Card> cards, Card curCard) {
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

    @Override
    public boolean match111(List<Card> cards, Card curCard) {
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

    @Override
    public boolean matchPair(List<Card> cards, TwoTuple<Card, Card> pair) {
        cards.remove(pair.getFirst());
        cards.remove(pair.getSecond());
        
        return true;
    }

    @Override
    public boolean canMatch111(List<Card> cards, Card source) {
        int count = 0;
        
        for (Card d : cards) {
            if (d.getValue() == source.getValue()) {
                count++;
            }
        }
        
        return count >= 3;
    }

    @Override
    public boolean canMatch123(List<Card> cards, Card source) {
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

    @Override
    public boolean canMatch2710(List<Card> cards, Card source) {
        int[] values = new int[]{2, 7 ,10};
        
        boolean exist = false;
        for (int v : values) {
            if (v == source.getValue()) {
                exist = true;
            }
        }
        
        if (!exist) {
            return false;
        }
        
        List<Card> tmp = new ArrayList<>();
        for (int v : values) {
            Card card = findValue(cards, v, source.getType());
            if (card != null) {
                tmp.add(card);
            }
        }
        
        return tmp.size() == values.length;
    }

    @Override
    public boolean canMatchPair(List<Card> cards, Card source) {
        int count = 0;
        for (Card d : cards) {
            if (source.isSame(d)) {
                count++;
            }
        }
        return count >= 2;
    }

    @Override
    public Card findValue(List<Card> cards, int value) {
        for (Card d : cards) {
            if (d.getValue() == value) {
                return d;
            }
        }
        return null;
    }

    @Override
    public Card findValue(List<Card> cards, int value, CardType type) {
        for (Card d : cards) {
            if (d.getValue() == value && d.getType() == type) {
                return d;
            }
        }
        return null;
    }

    @Override
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
}

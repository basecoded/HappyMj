package com.s4game.server.public_.card.model.group;

import java.util.List;

import com.s4game.server.public_.card.model.card.Card;

/**
 * 牌组
 * 
 * @author zeusgooogle@gmail.com
 * @sine 2016年10月10日 下午5:17:51
 */
public class DefaultCardGroup extends AbstractCardGroup {

    public DefaultCardGroup(CardGroupType type, List<Card> cards) {
        super(type, cards);
    }

}

package com.s4game.server.public_.card.model.card;

import com.s4game.server.public_.card.model.card.CardType;

/**
 * 一张牌
 * 
 * @author zeusgooogle@gmail.com
 * @sine 2016年10月10日 下午5:19:43
 */
public class Card {

    private String id;

    private int value;

    private CardType type;

    private CardState state;

    /**
     * 默认为空，分配到玩家手上后设置为玩家ID
     */
    private String roleId;
    
    public Card() {

    }

    public Card(String id, int value, CardType type, CardState state) {
        this.id = id;
        this.value = value;
        this.type = type;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public CardState getState() {
        return state;
    }

    public void setState(CardState state) {
        this.state = state;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getIdentity() {
        return value + "-" + type;
    }

    public boolean isSame(Card card) {
        return value == card.getValue() && type == card.getType();
    }

    @Override
    public String toString() {
        return "Card [id=" + id + ", value=" + value + ", type=" + type + ", state=" + state + "]";
    }

}

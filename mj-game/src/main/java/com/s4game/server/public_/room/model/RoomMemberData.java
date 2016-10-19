package com.s4game.server.public_.room.model;

import java.util.ArrayList;
import java.util.List;

import com.s4game.server.bus.role.export.RoleWrapper;
import com.s4game.server.public_.card.model.card.Card;
import com.s4game.server.public_.card.model.card.CardState;
import com.s4game.server.public_.card.model.group.CardGroupType;
import com.s4game.server.public_.card.model.group.DefaultCardGroup;
import com.s4game.server.public_.card.model.group.ICardGroup;

public class RoomMemberData {

    private String roleId;

    private String name;

    private String face;

    /**
     * 是否已准备（在线）
     */
    private boolean ready;

    /**
     * 庄家
     */
    private boolean dealer;

    /**
     * 玩家手牌
     */
    private DefaultCardGroup handCard;
    
    /**
     * 龙，碰，坎，吃，弃牌分组
     */
    private List<ICardGroup> groups = new ArrayList<>();

    public RoomMemberData(RoleWrapper role) {
        this.roleId = role.getId();
        this.name = role.getName();
        this.face = role.getFace();
    }

    public String getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public String getFace() {
        return face;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isDealer() {
        return dealer;
    }

    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }

    public DefaultCardGroup getHandCard() {
        return handCard;
    }

    public void addHandCard(Card card) {
        if (handCard == null) {
            handCard = new DefaultCardGroup(CardGroupType.HAND, new ArrayList<>());
        }
        
        card.setRoleId(roleId);
        card.setState(CardState.HAND_CARD);
        
        this.handCard.addCard(card);
    }

    public List<ICardGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<ICardGroup> groups) {
        this.groups = groups;
    }

}

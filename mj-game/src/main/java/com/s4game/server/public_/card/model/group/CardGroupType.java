package com.s4game.server.public_.card.model.group;

import com.s4game.core.enum_.EnumUtils;
import com.s4game.core.enum_.IntEnum;

/**
 * 牌组类型
 * 
 * @author zeusgooogle@gmail.com
 * @sine 2016年10月9日 下午8:35:19
 */
public enum CardGroupType implements IntEnum {

    /**
     * 手牌
     */
    HAND(1, false),
    
    /**
     * 提龙
     */
    LONG(2, true),

    /**
     * 跑
     */
    PAO(3, true),

    /**
     * 坎
     */
    KAN(4, false),

    /**
     * 碰
     */
    PENG(5, true),
    
    /**
     * 吃
     */
    CHI(6, true),
    
    /**
     * 弃牌
     */
    DISCARD(7, true),
    
    ;

    private static CardGroupType[] INDEXS = EnumUtils.toArray(values());

    private final int type;
    
    private final boolean broadcast; // 是否广播

    private CardGroupType(int type, boolean broadcast) {
        this.type = type;
        this.broadcast = broadcast;
    }

    @Override
    public int getId() {
        return type;
    }

    public boolean isBroadcast() {
        return broadcast;
    }

    public static CardGroupType findById(int value) {
        if (value < 0 || value >= INDEXS.length) {
            return null;
        }
        return INDEXS[value];
    }
}

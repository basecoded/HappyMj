package com.s4game.server.public_.card.model.card;

import com.s4game.core.enum_.EnumUtils;
import com.s4game.core.enum_.IntEnum;

/**
 * 类型
 * 
 * @author zeusgooogle@gmail.com
 * @sine 2016年10月9日 下午8:35:19
 */
public enum CardType implements IntEnum {

    /** 条 */
    TIAO(1, true),
    
    /** 万 */
    WAN(2, true),
    
    /** 筒 */
    TONG (3, true),
    
    /** 东 */
    DONG(4, false),
    
    /** 南 */
    NAN(5, false),
    
    /** 西 */
    XI(6, false),
    
    /** 北 */
    BEI(7, false),
    
    /** 中 */
    ZHONG(8, false),
    
    /** 发 */
    FA(9, false),
    
    /** 白 */
    BAI(10, false),
    
    
    ;
    private static CardType[] INDEXS = EnumUtils.toArray(values());
    
    private final int type;
    
    /**
     * 数字牌
     */
    private final boolean numeric;
    
    private CardType(int type, boolean numeric) {
        this.type = type;
        this.numeric = numeric;
    }

    @Override
    public int getId() {
        return type;
    }
    
    public boolean isNumeric() {
        return numeric;
    }
    
    public static CardType findById(int value) {
        if (value < 0 || value >= INDEXS.length) {
            return null;
        }
        return INDEXS[value];
    }
}

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

    SMALL(1),
    
    BIG (2)
    
    ;
    
    private static CardType[] INDEXS = EnumUtils.toArray(values());
    
    private final int type;
    
    private CardType(int type) {
        this.type = type;
    }

    @Override
    public int getId() {
        return type;
    }
    
    public static CardType findById(int value) {
        if (value < 0 || value >= INDEXS.length) {
            return null;
        }
        return INDEXS[value];
    }
}

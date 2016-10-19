package com.s4game.server.public_.card.model.card;

import com.s4game.core.enum_.EnumUtils;
import com.s4game.core.enum_.IntEnum;

/**
*
* @author zeusgoogogle@gmail.com
* @sine 2016年9月24日 下午8:39:40
*
*/
public enum CardBehavior implements IntEnum {
	
	/**
	 * 过
	 */
	PASS(1, false),
	
	/**
	 * 吃
	 */
	CHI(2, false),
	
	/**
	 * 碰
	 */
	PENG(4, false),
	
	/**
	 * 坎
	 */
	KAN(8, true),
	
	/**
	 * 跑
	 */
	PAO(16, true),
	
	/**
	 * 提龙
	 */
	LONG(32, true),
	
	/**
	 * 胡牌
	 */
	HU(64, true),
	
	;
	
	private static CardBehavior[] INDEXS = EnumUtils.toArray(values());
	
	private final int id;
	
	private final boolean auto;
	
	private CardBehavior(int id, boolean auto) {
		this.id = id;
		this.auto = auto;
	}

	@Override
	public int getId() {
		return id;
	}
	
	public boolean isAuto() {
	    return auto;
	}
	
	public static CardBehavior findById(int value) {
        if (value < 0 || value >= INDEXS.length) {
            return null;
        }
        return INDEXS[value];
    }
}

package com.s4game.core.enum_;

import java.lang.reflect.Array;

/**
 *
 * @author zeusgoogogle@gmail.com
 * @sine 2016年9月24日 下午8:44:14
 *
 */
public class EnumUtils {

	private static final int MAX_INDEX = 9999;

	/**
	 * 将实现{@link IntEnum}接口的枚举转成数组，数组下标取{@link IntEnum#getValue()}的值。
	 * 
	 * @param enums
	 * @return
	 */
	public static <E extends IntEnum> E[] toArray(E[] enums) {
		int maxIndex = 0;
		for (E e : enums) {
			int curIdx = e.getId();

			if (curIdx < 0) {
				throw new IndexOutOfBoundsException("Enum index cannot be negative: Type=" + e.getClass() + ", index=" + curIdx);
			}

			if (curIdx > MAX_INDEX) {
				throw new IllegalStateException("Enum index is too big: Type=" + e.getClass() + ", index=" + curIdx);
			}

			if (curIdx > maxIndex) {
				maxIndex = curIdx;
			}

		}

		@SuppressWarnings("unchecked")
		E[] enumArray = (E[]) Array.newInstance(enums.getClass().getComponentType(), maxIndex + 1);
		for (E e : enums) {
			int curIdx = e.getId();

			E oldenum = enumArray[curIdx];
			if (oldenum != null) {
				throw new IllegalStateException("Enum has duplicate index: Type=" + e.getClass() + ", index=" + curIdx);
			}
			enumArray[curIdx] = e;
		}
		return enumArray;
	}

}

package com.s4game.server.utils;


import java.math.BigDecimal;
import java.math.RoundingMode;

public final class MathUtils {

    private MathUtils() {
        
    }

    /**
     * 在[low, hi]之间随机一个数
     * 
     * @param low
     * @param hi
     * @return
     */
    public static int random(int low, int hi) {
        if (low == hi) {
            return low;
        }
        double c = low + (hi - low + 1) * Math.random();
        return c > hi ? hi : (int) c;
    }
    
    /**
     * 在[low, hi]之间随机一个数
     * 
     * @param low
     * @param hi
     * @return
     */
    public static long random(long low, long hi) {
        if (low == hi) {
            return low;
        }
        long c = (long) (low + (hi - low + 1) * Math.random());
        return c > hi ? hi : c;
    }

    /**
     * 在[low, hi)之间随机一个数
     * 
     * @param low
     * @param hi
     * @return
     */
    public static double random(double low, double hi) {
        if (low == hi) {
            return low;
        }
        return low + (hi - low) * Math.random();
    }

    /**
     * 在[low, hi)之间随机一个数
     * 
     * @param low
     * @param hi
     * @param scale
     *            保留小数位
     * @return
     */
    public static double random(double low, double hi, int scale) {
        if (low == hi) {
            return low;
        }
        double v = low + (hi - low) * Math.random();
        BigDecimal bd = new BigDecimal(Double.toString(v));
        bd = bd.setScale(scale, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * 在[low, hi)之间随机一个数
     * 
     * @param low
     * @param hi
     * @return
     */
    public static float random(float low, float hi) {
        if (low == hi) {
            return low;
        }
        return (float) (low + (hi - low) * Math.random());
    }

    /**
     * 在[low, hi)之间随机一个数
     * 
     * @param low
     * @param hi
     * @param scale
     *            保留小数位
     * @return
     */
    public static float random(float low, float hi, int scale) {
        if (low == hi) {
            return low;
        }
        double v = low + (hi - low) * Math.random();
        BigDecimal bd = new BigDecimal(Double.toString(v));
        bd = bd.setScale(scale, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

}

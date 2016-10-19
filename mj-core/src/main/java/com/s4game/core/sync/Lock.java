package com.s4game.core.sync;

/**
 * @author zeusgooogle
 * @date 2014-10-2 下午07:40:01
 */
public class Lock {

    private long timestamp = System.currentTimeMillis();

    private static final long idle = 3600000L;

    private String key;

    public Lock(String paramString) {
        this.key = paramString;
    }

    public String getKey() {
        return this.key;
    }

    public void update() {
        this.timestamp = System.currentTimeMillis();
    }

    public boolean canClean() {
        return System.currentTimeMillis() - this.timestamp > idle;
    }

}

package com.s4game.server.utils.id;

import java.util.concurrent.atomic.AtomicLong;

public class Id {
    
    private String prefix;
    private AtomicLong increase = new AtomicLong();

    public Id(String prefix) {
        this.prefix = prefix;
    }

    public String nextString() {
        return this.prefix + "_" + new Long(this.increase.getAndIncrement()).toString();
    }

    public long nextLong() {
        return this.increase.getAndIncrement();
    }
}

package com.s4game.server.utils.id;

public class IdUtil {
    
    private static ThreadLocal<Id> IdLocal = new ThreadLocal<Id>() {
        protected Id initialValue() {
            return new Id(String.valueOf(Thread.currentThread().getId()));
        }
    };

    public static String nextString(String component) {
        return component + "_" + ((Id) IdLocal.get()).nextString();
    }
}

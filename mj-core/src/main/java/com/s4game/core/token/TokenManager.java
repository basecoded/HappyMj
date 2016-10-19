package com.s4game.core.token;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zeusgooogle
 * @date 2014-9-30 下午04:54:21
 */
public class TokenManager {

    private ConcurrentMap<String, ConcurrentMap<String, Long>> componentsTokenMap = new ConcurrentHashMap<String, ConcurrentMap<String, Long>>();

    public TokenManager() {

    }

    public Long createToken(String component, String taskId) {
        ConcurrentMap<String, Long> map = getComponentTokens(component);
        synchronized (map) {
            long time = System.currentTimeMillis();
            map.put(taskId, time);
            return time;
        }
    }

    public void removeToken(String component, String taskId) {
        ConcurrentMap<String, Long> map = getComponentTokens(component);
        synchronized (map) {
            map.remove(taskId);
        }
    }

    public boolean checkToken(long time, String component, String taskId) {
        ConcurrentMap<String, Long> map = getComponentTokens(component);
        synchronized (map) {
            Long localLong = map.get(taskId);
            if (null == localLong) {
                return false;
            }
            return time == localLong;
        }
    }

    private ConcurrentMap<String, Long> getComponentTokens(String component) {
        ConcurrentMap<String, Long> map = this.componentsTokenMap.get(component);
        if (null == map) {
            synchronized (this.componentsTokenMap) {
                map = this.componentsTokenMap.get(component);
                if (null == map) {
                    map = new ConcurrentHashMap<String, Long>();
                    this.componentsTokenMap.put(component, map);
                }
            }
        }
        return map;
    }

}

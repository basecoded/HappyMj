package com.s4game.core.sync;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zeusgooogle
 * @date 2014-10-2 下午07:40:53
 */
public class LockManager {

    private Logger LOG = LoggerFactory.getLogger(getClass());

    private ConcurrentMap<String, ConcurrentMap<String, Lock>> components = new ConcurrentHashMap<>();

    private static final long CLEAN_PERIOD = 1800000L;

    public LockManager() {
        Thread thread = new Thread("LockManager-Cleaner") {

            public void run() {
                try {
                    for (;;) {
                        Thread.sleep(CLEAN_PERIOD);
                        try {
                            clean();
                        } catch (Exception e) {
                            LOG.error("", e);
                        }
                    }
                } catch (Exception e) {
                    LOG.error("", e);
                }
            }
        };

        thread.setDaemon(true);
        thread.start();
    }

    public Lock getLock(String component, String key) {
        ConcurrentMap<String, Lock> map = getComponentLocks(component);
        synchronized (map) {
            Lock lock = map.get(key);
            if (null == lock) {
                lock = new Lock(key);
                map.put(key, lock);
            }

            lock.update();
            return lock;
        }
    }

    private ConcurrentMap<String, Lock> getComponentLocks(String component) {
        ConcurrentMap<String, Lock> map = this.components.get(component);
        if (null == map) {
            synchronized (this.components) {
                map = this.components.get(component);
                if (null == map) {
                    map = new ConcurrentHashMap<String, Lock>();
                    this.components.put(component, map);
                }
            }
        }
        return map;
    }

    public void clean() {
        int i = 0;
        long l = 0L;
        Iterator<ConcurrentMap<String, Lock>> iterator = this.components.values().iterator();
        while (iterator.hasNext()) {
            ConcurrentMap<String, Lock> locks = iterator.next();
            synchronized (locks) {
                Iterator<Lock> iterator2 = locks.values().iterator();
                while (iterator2.hasNext()) {
                    Lock localLock = iterator2.next();
                    if (localLock.canClean()) {
                        locks.remove(localLock.getKey());
                        i++;
                    }
                }
                l += locks.size();
            }
        }
        
        LOG.error("LockManager-Cleaner:cleaned {}, remain {}", new Object[] { Integer.valueOf(i), Long.valueOf(l) });
    }

}

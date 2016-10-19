package com.s4game.core.sync.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import com.s4game.core.sync.Lock;
import com.s4game.core.sync.LockManager;
import com.s4game.core.sync.annotation.Sync;

/**
 * @author zeusgooogle
 * @date 2014-10-2 下午07:47:00
 */
public class SyncAspect {

    private LockManager lockManager = null;

    public SyncAspect() {
    }

    public void setLockManager(LockManager lockManager) {
        this.lockManager = lockManager;
    }

    public Object sync(ProceedingJoinPoint proceedingJoinPoint, Sync sync) {
        String key = getLockKey(sync, proceedingJoinPoint.getArgs());
        Lock lock = this.lockManager.getLock(sync.component(), key);

        synchronized (lock) {
            try {
                return proceedingJoinPoint.proceed();
            } catch (Throwable t) {
                throw new RuntimeException("error in aop sync invoke, " + t.getMessage(), t);
            }
        }
    }

    private String getLockKey(Sync sync, Object[] args) {
        int[] indexs = sync.indexes();

        if ((null != indexs) && (indexs.length > 0)) {
            StringBuilder sb = new StringBuilder();
            for (int k : indexs) {
                Object object = args[k];
                sb.append(object);
            }
            return sb.toString();
        }

        return sync.component();
    }
}

package com.s4game.core.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author zeusgooogle
 * @date 2014-9-30 下午04:47:30
 */
public class ScheduleExecutor {

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public ScheduleExecutor() {
    }

    public ScheduledFuture<?> schedule(Runnable runnable, long delay, TimeUnit timeUnit) {
        return getService().schedule(runnable, delay, timeUnit);
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long initialDelay, long period, TimeUnit timeUnit) {
        return getService().scheduleAtFixedRate(runnable, initialDelay, period, timeUnit);
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long initialDelay, long period, TimeUnit timeUnit) {
        return getService().scheduleAtFixedRate(runnable, initialDelay, period, timeUnit);
    }

    private ScheduledExecutorService getService() {
        return this.executorService;
    }

}

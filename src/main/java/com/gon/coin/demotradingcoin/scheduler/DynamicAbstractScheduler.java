package com.gon.coin.demotradingcoin.scheduler;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

public abstract class DynamicAbstractScheduler {
    private ConcurrentTaskScheduler scheduler;



    public void startScheduler() {
        scheduler = new ConcurrentTaskScheduler();
        scheduler.schedule(getRunnable(), getTrigger());
    }

    private Runnable getRunnable(){
        return new Runnable(){
            @Override
            public void run() {
                runner();
            }
        };
    }

    public abstract void runner();

    public abstract Trigger getTrigger();
}

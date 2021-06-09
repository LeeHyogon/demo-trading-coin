package com.gon.coin.demotradingcoin.scheduler;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class TradePriceScheduler extends DynamicAbstractScheduler{
    // 실행로직
    @Override
    public void runner() {
        System.out.println(new Date());

    }
    // 실행주기
    @Override
    public Trigger getTrigger() {
        return new PeriodicTrigger(1, TimeUnit.SECONDS);
    }
}

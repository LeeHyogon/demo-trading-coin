package com.gon.coin.demotradingcoin.service;


import com.gon.coin.demotradingcoin.scheduler.TradePriceScheduler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TradePriceSechdulerService {
    private final TradePriceScheduler tradePriceScheduler;

    public void runSchedule(){
        tradePriceScheduler.startScheduler();
    }
}

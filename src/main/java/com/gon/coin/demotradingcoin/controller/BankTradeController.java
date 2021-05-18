package com.gon.coin.demotradingcoin.controller;

import com.gon.coin.demotradingcoin.service.BankTradeSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BankTradeController {
    private final BankTradeSerivce bankTradeSerivce;

    @GetMapping("/bank")
    public String bank(){
        return "/bank/deposit";
    }
}

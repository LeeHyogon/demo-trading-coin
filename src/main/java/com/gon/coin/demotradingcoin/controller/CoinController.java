package com.gon.coin.demotradingcoin.controller;

import com.gon.coin.demotradingcoin.domain.Coin;

import com.gon.coin.demotradingcoin.service.CoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CoinController {


    private final CoinService coinService;

    @GetMapping("/")
    public String list(Model model) {
        List<Coin> coins = coinService.findAll();
        model.addAttribute("coins", coins);
        return "home";
    }
}

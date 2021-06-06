package com.gon.coin.demotradingcoin.controller;

import com.gon.coin.demotradingcoin.domain.upbitcoin.UpbitCoin;
import com.gon.coin.demotradingcoin.domain.upbitcoin.dto.UpbitCoinResponseNameDto;
import com.gon.coin.demotradingcoin.repository.UpbitCoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class DayItemController {
    private final UpbitCoinRepository upbitCoinRepository;

    @GetMapping("/exchange/{code}")
    public String exchange(@PathVariable("code")String code, Model model){
        List<UpbitCoin> all=upbitCoinRepository.findAll();
        List<UpbitCoinResponseNameDto> upbitcoins=all.stream()
                .map(o->new UpbitCoinResponseNameDto(o))
                .collect(Collectors.toList());
        model.addAttribute("upbitcoins",upbitcoins);
        model.addAttribute("code",code);
        return "exchange/main";
    }
    @GetMapping("/chart")
    public String chart(){
        return "chart/dayChart";
    }
}

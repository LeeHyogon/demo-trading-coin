package com.gon.coin.demotradingcoin.controller;

import com.gon.coin.demotradingcoin.domain.Member;
import com.gon.coin.demotradingcoin.service.BankTradeSerivce;
import com.gon.coin.demotradingcoin.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class BankTradeController {
    private final BankTradeSerivce bankTradeSerivce;
    private final MemberService memberService;

    @GetMapping("/bank")
    public String bank(Model model){
        Member member = memberService.currentUser().get();
        model.addAttribute("name",member.getUsername());
        model.addAttribute("money",member.getAccount().getBalance());
        return "bank/trade";
    }
    @GetMapping("/bank/trade")
    public String trade(@RequestParam("krw") Double krw, Model model){
        Member member = memberService.currentUser().get();
        memberService.deposit(member,krw);
        model.addAttribute("name",member.getUsername());
        model.addAttribute("money",member.getAccount().getBalance());
        return "bank/trade";
    }
    @GetMapping("/bank/deposit")
    public String deposit(@RequestParam("krw") Double krw, Model model){
        Member member = memberService.currentUser().get();
        memberService.deposit(member,krw);
        model.addAttribute("name",member.getUsername());
        model.addAttribute("money",member.getAccount().getBalance());
        return "bank/trade";
    }
    @GetMapping("/bank/drawal")
    public String drawal(@RequestParam("krw") Double krw, Model model){
        Member member = memberService.currentUser().get();
        memberService.drawal(member,krw);
        model.addAttribute("name",member.getUsername());
        model.addAttribute("money",member.getAccount().getBalance());
        return "bank/trade";
    }
}


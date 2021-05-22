package com.gon.coin.demotradingcoin.controller;

import com.gon.coin.demotradingcoin.domain.Member;
import com.gon.coin.demotradingcoin.service.BankTradeSerivce;
import com.gon.coin.demotradingcoin.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;

@Controller
@RequiredArgsConstructor
public class BankTradeController {
    private final BankTradeSerivce bankTradeSerivce;
    private final MemberService memberService;

    @GetMapping("/bank")
    public String bank(Model model){
        Member member = memberService.currentUser().get();
        model.addAttribute("name",member.getUsername());
        model.addAttribute("money",member.getAccount().getSumOfMoney());
        return "bank/deposit";
    }

    @GetMapping("/bank/deposit")
    public String deposit(@RequestParam("krw") int krw, Model model){
        Member member = memberService.currentUser().get();
        memberService.deposit(member,krw);
        model.addAttribute("name",member.getUsername());
        model.addAttribute("money",member.getAccount().getSumOfMoney());
        return "bank/deposit";
    }
}

package com.gon.coin.demotradingcoin.controller;

import com.gon.coin.demotradingcoin.domain.banktransactions.BankTransaction;
import com.gon.coin.demotradingcoin.domain.banktransactions.BankTransactionStatus;
import com.gon.coin.demotradingcoin.domain.Member;
import com.gon.coin.demotradingcoin.domain.banktransactions.dto.BankTransactionListDto;
import com.gon.coin.demotradingcoin.repository.BankTransactionRepository;
import com.gon.coin.demotradingcoin.repository.MemberRepository;
import com.gon.coin.demotradingcoin.service.BankTransactionService;
import com.gon.coin.demotradingcoin.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BankTransactionController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final BankTransactionService bankTransactionService;
    private final BankTransactionRepository bankTransactionRepository;

    @GetMapping("/bank")
    public String bank(Model model){
        Member member = memberService.currentUser().get();
        model.addAttribute("name",member.getUsername());
        model.addAttribute("money",member.getAccount().getBalance());
        return "bank/trade";
    }

    @GetMapping("/bank/deposit")
    public String deposit(@RequestParam("krw") Double krw, Model model){
        Member member = memberService.currentUser().get();
        bankTransactionService.addList(member,krw, BankTransactionStatus.DEPOSIT);
        model.addAttribute("name",member.getUsername());
        model.addAttribute("money",member.getAccount().getBalance());
        return "redirect:/bank";
    }
    @GetMapping("/bank/drawal")
    public String drawal(@RequestParam("krw") Double krw, Model model){
        Member member = memberService.currentUser().get();
        bankTransactionService.addList(member,krw, BankTransactionStatus.DRAWAL);
        model.addAttribute("name",member.getUsername());
        model.addAttribute("money",member.getAccount().getBalance());
        return "redirect:/bank";
    }
    @GetMapping("/bank/list")
    public String bankList(Model model){
        Member member = memberService.currentUser().get();
        //List<BankTransaction> transactions =bankTransactionService.findByUsername(member.getUsername());

        List<BankTransaction> results =member.getBankTransactions();
        List<BankTransactionListDto> transactions=results.stream()
                .map(BankTransactionListDto::new)
                .collect(Collectors.toList());


        model.addAttribute("name",member.getUsername());
        model.addAttribute("money",member.getAccount().getBalance());
        model.addAttribute("transactions",transactions);
        return "bank/list";
    }
}

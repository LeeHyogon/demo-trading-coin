package com.gon.coin.demotradingcoin.controller;

import com.gon.coin.demotradingcoin.domain.Address;
import com.gon.coin.demotradingcoin.domain.Member;
import com.gon.coin.demotradingcoin.dto.MemberDto;
import com.gon.coin.demotradingcoin.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /*
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }*/
    @GetMapping("/members/signup")
    public String createForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "/members/signupForm";
    }

    @PostMapping("/members/signup")
    public String create(@Valid MemberDto memberDto, BindingResult result){

        if(result.hasErrors()){
            return "/members/signupForm";
        }
        memberService.signUp(memberDto);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
    @GetMapping("/members/login")
    public String loginForm() {
        return "/members/loginForm";
    }

    @PostMapping("/members/login")
    public String login() {
        return "redirect:/";
    }
}


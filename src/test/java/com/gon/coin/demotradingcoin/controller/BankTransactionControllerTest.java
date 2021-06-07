package com.gon.coin.demotradingcoin.controller;

import com.gon.coin.demotradingcoin.repository.MemberRepository;
import com.gon.coin.demotradingcoin.service.MemberService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BankTransactionControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 입금확인() throws Exception {
        String url= "/bank/deposit";


    }
}
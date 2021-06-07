package com.gon.coin.demotradingcoin.service;

import com.gon.coin.demotradingcoin.config.Role;
import com.gon.coin.demotradingcoin.domain.Account;
import com.gon.coin.demotradingcoin.domain.Member;
import com.gon.coin.demotradingcoin.exception.NotEnoughMoneyException;
import com.gon.coin.demotradingcoin.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 입금확인() throws Exception {
        //given
        Account account=new Account("우리","123456", Double.valueOf(1000));
        Member member=new Member("memberA","123456",Role.MEMBER,account);
        Double krw=Double.valueOf(5000);
        //when
        //memberService.deposit(member,krw);
        //then
        assertEquals("입금계좌에 넣은만큼 금액늘어나야됨",Double.valueOf(6000),member.getAccount().getBalance());
    }
    @Test
    public void 출금확인() throws Exception {
        //given
        Account account=new Account("우리","123456", Double.valueOf(10000));
        Member member=new Member("memberA","123456",Role.MEMBER,account);
        Double krw=Double.valueOf(3000);
        //when
        //memberService.drawal(member,krw);
        //then
        assertEquals("입금계좌에 넣은만큼 금액늘어나야됨",Double.valueOf(7000),member.getAccount().getBalance());
    }
    @Test(expected = NotEnoughMoneyException.class)
    public void 출금확인_계좌잔액초과() throws Exception {
        //given
        Account account=new Account("우리","123456", Double.valueOf(10000));
        Member member=new Member("memberA","123456",Role.MEMBER,account);
        Double krw=Double.valueOf(11000);
        //when
        //memberService.drawal(member,krw);
        //then
        fail("잔액이 출금 금액보다 적습니다.");
    }
}
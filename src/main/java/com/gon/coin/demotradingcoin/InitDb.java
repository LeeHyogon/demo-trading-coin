package com.gon.coin.demotradingcoin;

import com.gon.coin.demotradingcoin.domain.Account;
import com.gon.coin.demotradingcoin.domain.Coin;
import com.gon.coin.demotradingcoin.domain.Member;
import com.gon.coin.demotradingcoin.domain.upbitcoin.UpbitCoin;
import com.gon.coin.demotradingcoin.dto.MemberDto;
import com.gon.coin.demotradingcoin.service.MemberService;
import com.gon.coin.demotradingcoin.service.UpbitCoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.math.BigInteger;


@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;
    private final UpbitCoinService upbitCoinService;
    @PostConstruct
    public void init(){

        initService.dbInit3();
        initService.dbInit4();
        upbitCoinService.saveCoinNameURL();
        upbitCoinService.saveDayItemURL();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;
        private final MemberService memberService;

        public void dbInit3(){
            Account account1 = createAccount("국민", "123456", Double.valueOf(1000));
            em.persist(account1);
            MemberDto memberA=createMember("memberA","1234");
            memberService.signUp(memberA);
            Member member = memberService.findByName(memberA.getUsername());
            member.setAccount(account1);
        }
        public void dbInit4(){
            Account account1 = createAccount("우리", "703456", Double.valueOf(2000));
            em.persist(account1);
            MemberDto memberB=createMember("memberB","1234");
            memberService.signUp(memberB);
            Member member = memberService.findByName(memberB.getUsername());
            member.setAccount(account1);
        }


        private MemberDto createMember(String username, String password) {
            MemberDto memberDto = new MemberDto(username,password);
            return memberDto;
        }
        private Account createAccount(String bankName,String bankCode,Double SumOfMoney){
            Account account= new Account(bankName,bankCode,SumOfMoney);

            return account;
        }


    }

}

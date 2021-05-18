package com.gon.coin.demotradingcoin;

import com.gon.coin.demotradingcoin.domain.Account;
import com.gon.coin.demotradingcoin.domain.Coin;
import com.gon.coin.demotradingcoin.domain.Member;
import com.gon.coin.demotradingcoin.dto.MemberDto;
import com.gon.coin.demotradingcoin.service.MemberService;
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

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
        initService.dbInit3();
        initService.dbInit4();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;
        private final MemberService memberService;
        public void dbInit1(){
            Coin coin =createCoin("BitCoin",58000000,"bitcoin_icon.png");
            em.persist(coin);
        }
        public void dbInit2(){
            Coin coin =createCoin("ethereum",4100000,"etherium_icon.png");
            em.persist(coin);
        }
        public void dbInit3(){
            Account account1 = createAccount("국민", "123456", BigInteger.valueOf(1000000000));
            em.persist(account1);
            MemberDto memberA=createMember("memberA","1234");
            memberService.signUp(memberA);
            Member member = memberService.findByName(memberA.getUsername());
            member.setAccount(account1);
        }
        public void dbInit4(){
            Account account1 = createAccount("우리", "703456", BigInteger.valueOf(2000000000));
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
        private Account createAccount(String bankName,String bankCode,BigInteger SumOfMoney){
            Account account= new Account(bankName,bankCode,SumOfMoney);

            return account;
        }

        private Coin createCoin(String name,int price, String icon_url) {
            Coin coin=new Coin(name,price,icon_url);

            return coin;
        }
    }

}

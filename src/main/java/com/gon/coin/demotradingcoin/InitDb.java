package com.gon.coin.demotradingcoin;

import com.gon.coin.demotradingcoin.domain.Account;
import com.gon.coin.demotradingcoin.domain.Coin;
import com.gon.coin.demotradingcoin.domain.Member;
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
        public void dbInit1(){
            Coin coin =createCoin("BitCoin",58000000,"bitcoin_icon.png");
            em.persist(coin);
        }
        public void dbInit2(){
            Coin coin =createCoin("ethereum",4100000,"etherium_icon.png");
            em.persist(coin);
        }
        public void dbInit3(){
            Account account1 = createAccount("국민", "123456", BigInteger.valueOf(0));
            em.persist(account1);
            Member memberA=createMember("memberA","1234",account1);
            em.persist(memberA);
        }

        public void dbInit4(){
            Account account2 = createAccount("우리", "703456", BigInteger.valueOf(1));
            em.persist(account2);
            Member memberB=createMember("memberB","1234",account2);
            em.persist(memberB);
        }

        private Member createMember(String username, String password, Account account) {
            Member member = new Member();
            member.setUsername(username);
            member.setPassword(password);
            member.setAccount(account);

            return member;
        }
        private Account createAccount(String bankName,String bankCode,BigInteger SumOfMoney){
            Account account= new Account();
            account.setBankName(bankName);
            account.setBankCode(bankCode);
            account.setSumOfMoney(SumOfMoney);
            return account;
        }

        private Coin createCoin(String name,int price, String icon_url) {
            Coin coin=new Coin();
            coin.setName(name);
            coin.setPrice(price);
            coin.setIcon_url(icon_url);
            return coin;
        }
    }

}


package com.gon.coin.demotradingcoin;

import com.gon.coin.demotradingcoin.domain.Coin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;



@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
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
        private Coin createCoin(String name,int price, String icon_url) {
            Coin coin=new Coin();
            coin.setName(name);
            coin.setPrice(price);
            coin.setIcon_url(icon_url);
            return coin;
        }
    }

}


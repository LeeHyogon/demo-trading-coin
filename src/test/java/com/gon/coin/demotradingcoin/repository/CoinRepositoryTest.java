package com.gon.coin.demotradingcoin.repository;

import com.gon.coin.demotradingcoin.domain.Coin;
import com.gon.coin.demotradingcoin.domain.member.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class CoinRepositoryTest {

    @Autowired
    CoinRepository coinRepository;
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void 사용자이름으로보유코인목록찾는쿼리() throws Exception {
        String username="memberA";
        String market1="KRW-BTC";
        String market2="KRW-ETC";
        Member member=new Member(username);
        memberRepository.save(member);
        Coin coin1=new Coin().builder()
                .member(member)
                .market(market1)
                .build();
        Coin coin2=new Coin().builder()
                .member(member)
                .market(market2)
                .build();
        coinRepository.save(coin1);
        coinRepository.save(coin2);
        List<Coin> coins = coinRepository.haveCoinsFind(username);
        assertEquals(coins.size(),2);
        for (Coin coin: coins){
            assertEquals(coin.getMember().getUsername(),username);
            if(!(coin.getMarket()==market1 || coin.getMarket()==market2)){
                fail();
            }
        }
    }
    @Test
    public void 사용자이름으로특정코인찾는쿼리() throws Exception {
        String username="memberA";
        String market1="KRW-BTC";
        String market2="KRW-ETC";
        Member member=new Member(username);
        memberRepository.save(member);
        Coin coin1=new Coin().builder()
                .member(member)
                .market(market1)
                .build();
        Coin coin2=new Coin().builder()
                .member(member)
                .market(market2)
                .build();
        coinRepository.save(coin1);
        coinRepository.save(coin2);
        Optional<Coin> coinA = coinRepository.haveCoinFind(username, market1);
        assertEquals(coinA.get().getMarket(),market1);
        assertEquals(coinA.get().getMember().getUsername(),username);
        Optional<Coin> coinB = coinRepository.haveCoinFind(username, market2);
        assertEquals(coinB.get().getMarket(),market2);
        assertEquals(coinB.get().getMember().getUsername(),username);
    }


}
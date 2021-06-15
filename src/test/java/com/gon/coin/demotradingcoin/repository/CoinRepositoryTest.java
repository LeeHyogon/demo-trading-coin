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
    public void 사용자이름으로보유코인찾는쿼리() throws Exception {
        String username="memberA";
        Member member=new Member(username);
        memberRepository.save(member);
        Coin coin1=new Coin(member);
        Coin coin2=new Coin(member);
        coinRepository.save(coin1);
        coinRepository.save(coin2);
        List<Coin> coins = coinRepository.haveCoinFind(username);
        assertEquals(coins.size(),2);
        for (Coin coin: coins){
            assertEquals(coin.getMember().getUsername(),username);
        }

    }

}
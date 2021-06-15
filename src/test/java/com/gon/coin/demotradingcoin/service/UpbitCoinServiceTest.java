package com.gon.coin.demotradingcoin.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UpbitCoinServiceTest {

    @Autowired
    UpbitCoinService upbitCoinService;
    @Test
    public void 코인이름확인() throws Exception {
        String code="KRW-BTC";
        String koreanName="비트코인";
        String englishName="Bitcoin";
        //given
        List<String> ret=upbitCoinService.getCoinNames(code);
        //when
        String kor=ret.get(0);
        System.out.println(kor+"korean");
        String eng=ret.get(1);
        System.out.println(eng+"eng");
        //then
        assertEquals(kor,koreanName);
        assertEquals(eng,englishName);
    }
}
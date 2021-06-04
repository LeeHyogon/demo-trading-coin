package com.gon.coin.demotradingcoin.service;

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
public class UpbitCoinServiceTest {

    @Autowired
    UpbitCoinService upbitCoinService;
    @Test
    public void url확인() throws Exception {
        //given

        //when

        //then
    }
}
package com.gon.coin.demotradingcoin.repository;

import com.gon.coin.demotradingcoin.domain.Coin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CoinRepositoryTest {

    @Autowired
    CoinRepository coinRepository;

}
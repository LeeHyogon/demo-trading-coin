package com.gon.coin.demotradingcoin.service;


import com.gon.coin.demotradingcoin.domain.Coin;
import com.gon.coin.demotradingcoin.repository.CoinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CoinService {

    private final CoinRepository coinRepository;
    public List<Coin> findAll(){
        return coinRepository.findAll();
    }

}

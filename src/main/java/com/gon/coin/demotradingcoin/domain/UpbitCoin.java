package com.gon.coin.demotradingcoin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@ToString
@Builder
@Getter
public class UpbitCoin {
    @Id
    @GeneratedValue
    @Column(name = "upbit_coin_id")
    private Long id;

    private String MarketName;
    private String KoreanName;
    private String EnglishName;

}

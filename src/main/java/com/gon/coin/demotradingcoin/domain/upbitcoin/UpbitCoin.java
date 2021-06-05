package com.gon.coin.demotradingcoin.domain.upbitcoin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class UpbitCoin {
    @Id
    @GeneratedValue
    @Column(name = "upbit_coin_id")
    private Long id;

    private String market;
    private String koreanName;
    private String englishName;

    @Builder
    public UpbitCoin(String market, String koreanName, String englishName) {
        this.market = market;
        this.koreanName = koreanName;
        this.englishName = englishName;
    }
}

package com.gon.coin.demotradingcoin.domain.upbitcoin.dto;

import com.gon.coin.demotradingcoin.domain.upbitcoin.UpbitCoin;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpbitCoinResponseNameDto {
    private String market;
    private String koreanName;
    private String englishName;

    public UpbitCoinResponseNameDto(UpbitCoin upbitCoin) {
        this.market= upbitCoin.getMarket();
        this.koreanName= upbitCoin.getKoreanName();
        this.englishName= upbitCoin.getEnglishName();
    }
}

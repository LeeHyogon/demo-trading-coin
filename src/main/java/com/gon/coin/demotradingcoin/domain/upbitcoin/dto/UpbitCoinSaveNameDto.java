package com.gon.coin.demotradingcoin.domain.upbitcoin.dto;

import com.gon.coin.demotradingcoin.domain.upbitcoin.UpbitCoin;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpbitCoinSaveNameDto {
    private String market;
    private String koreanName;
    private String englishName;

    @Builder
    public UpbitCoinSaveNameDto(String market, String koreanName, String englishName) {
        this.market = market;
        this.koreanName = koreanName;
        this.englishName = englishName;
    }
    public UpbitCoin toEntity(){
        return UpbitCoin.builder()
                .market(market)
                .koreanName(koreanName)
                .englishName(englishName)
                .build();
    }

}

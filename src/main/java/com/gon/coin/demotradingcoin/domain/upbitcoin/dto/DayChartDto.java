package com.gon.coin.demotradingcoin.domain.upbitcoin.dto;

import com.gon.coin.demotradingcoin.domain.upbitcoin.ChangePriceStatus;
import com.gon.coin.demotradingcoin.domain.upbitcoin.UpbitCoin;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DayChartDto {
    private UpbitCoin upbitCoin;

    private String code;
    //국제 표준시
    private OffsetDateTime candleDateTime;
    //한국시
    private OffsetDateTime candleDateTimeKst;
    //시작가
    private Double openingPrice;
    //고가
    private Double highPrice;
    //저가
    private Double lowPrice;
    //현재가격정보
    private Double tradePrice;
    //누적체결량
    private Double candleAccTradeVolume;
    //누적체결대금
    private Double candleAccTradePrice;
    //전일 종가
    private Double prevClosingPrice;
    //전일 종가 대비 변화금액의 여부
    @Enumerated(EnumType.STRING)
    private ChangePriceStatus change;
    //전일 종가 대비 변화금액(절댓값)
    private Double changePrice;
    //부호가 있는 변화금액
    private Double signedChangePrice;
    //전일 종가 대비 변화량(절댓값)
    private Double changeRate;
    //부호가 있는 변화량
    private Double signedChangeRate;
}

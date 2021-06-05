package com.gon.coin.demotradingcoin.domain.upbitcoin;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Getter
@NoArgsConstructor
public class DayItem {
    @Id
    @GeneratedValue
    @Column(name = "day_item_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="upbit_coin_id")
    private  UpbitCoin upbitCoin;

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
    //UNIX타임스탬프,1970년1월1일부터얼마나 지났는지
    private Long timestamp;
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

    @Builder
    public DayItem(UpbitCoin upbitCoin, String code, OffsetDateTime candleDateTime, OffsetDateTime candleDateTimeKst, Double openingPrice, Double highPrice, Double lowPrice, Double tradePrice, Double candleAccTradeVolume, Double candleAccTradePrice, Long timestamp, Double prevClosingPrice, ChangePriceStatus change, Double changePrice, Double signedChangePrice, Double changeRate, Double signedChangeRate) {
        this.upbitCoin = upbitCoin;
        this.code = code;
        this.candleDateTime = candleDateTime;
        this.candleDateTimeKst = candleDateTimeKst;
        this.openingPrice = openingPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.tradePrice = tradePrice;
        this.candleAccTradeVolume = candleAccTradeVolume;
        this.candleAccTradePrice = candleAccTradePrice;
        this.timestamp = timestamp;
        this.prevClosingPrice = prevClosingPrice;
        this.change = change;
        this.changePrice = changePrice;
        this.signedChangePrice = signedChangePrice;
        this.changeRate = changeRate;
        this.signedChangeRate = signedChangeRate;
    }
}
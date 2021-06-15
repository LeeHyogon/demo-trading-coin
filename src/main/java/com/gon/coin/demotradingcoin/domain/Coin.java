package com.gon.coin.demotradingcoin.domain;


import com.gon.coin.demotradingcoin.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coin {
    @Id
    @GeneratedValue
    @Column(name = "coin_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String market;

    private String koreanName;

    private String englishName;

    //보유 수량
    private Double volume;

    //매수 평균가
    private Double averagePrice;

    //총매수 금액,매수금액(krw)
    private Double totalKrw;

    //----생성자----//
    //jpa 테스트용 생성자
    public Coin(Member member) {
        this.member=member;
    }

    public Coin(Member member, String market) {
        this.member = member;
        this.market = market;
    }
    public Coin(Member member, String market, String koreanName, String englishName, Double volume, Double averagePrice, Double totalKrw) {
        this.member = member;
        this.market = market;
        this.koreanName = koreanName;
        this.englishName = englishName;
        this.volume = volume;
        this.averagePrice = averagePrice;
        this.totalKrw = totalKrw;
    }

    //---비즈니스용 로직: 지정가 매수 시//
    public void update(Double tradingVolume, Double transactionPrice) {
        Double newVolume=volume+tradingVolume;
        Double addKrw=tradingVolume*transactionPrice;
        Double newAveragetPrice=((averagePrice*volume)+addKrw)/(newVolume);
        volume=newVolume;
        averagePrice=newAveragetPrice;
        totalKrw+=addKrw;
    }
}

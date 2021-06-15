package com.gon.coin.demotradingcoin.domain;


import com.gon.coin.demotradingcoin.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
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


    //jpa 테스트용 생성자
    public Coin(Member member) {
        this.member=member;
    }
}

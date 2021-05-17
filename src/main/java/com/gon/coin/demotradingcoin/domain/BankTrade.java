package com.gon.coin.demotradingcoin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class BankTrade {

    @Id
    @GeneratedValue
    @Column(name = "banktrade_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private BankTradeStatus status; //DEPOSIT,DRAWAL

}

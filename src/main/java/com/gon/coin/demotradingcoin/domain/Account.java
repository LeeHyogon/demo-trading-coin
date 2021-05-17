package com.gon.coin.demotradingcoin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @OneToOne(mappedBy="account",fetch=FetchType.LAZY)
    private Member member;

    private String bankName;
    private String bankCode;

    Long SumOfMoney;
}

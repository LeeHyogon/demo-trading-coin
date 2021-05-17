package com.gon.coin.demotradingcoin.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

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

    BigInteger SumOfMoney;

    public Account(String bankName, String bankCode, BigInteger sumOfMoney) {
        this.bankName = bankName;
        this.bankCode = bankCode;
        SumOfMoney = sumOfMoney;
    }

    public Account() {

    }
}

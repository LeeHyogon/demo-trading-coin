package com.gon.coin.demotradingcoin.domain;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @OneToOne(mappedBy="account",fetch=FetchType.LAZY)
    private Member member;

    private String bankName;
    private String bankCode;

    int SumOfMoney=0;
//   BigInteger SumOfMoney=BigInteger.valueOf(0);

    public Account(String bankName, String bankCode,int sumOfMoney) {
        this.bankName = bankName;
        this.bankCode = bankCode;
        SumOfMoney = sumOfMoney;
    }

    public Account() {

    }

    public void deposit(int krw) {
        this.SumOfMoney+=krw;
    }
}

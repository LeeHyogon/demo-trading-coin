package com.gon.coin.demotradingcoin.domain;

import com.gon.coin.demotradingcoin.exception.NotEnoughMoneyException;
import lombok.Getter;

import javax.persistence.*;

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

    Double balance;

    public Account(String bankName, String bankCode,Double balance) {
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.balance = balance;
    }

    public Account() {

    }

    public void deposit(Double krw) {
        this.balance +=krw;
    }

    public void drawal(Double krw){
        Double restMoney=this.balance-krw;
        if(restMoney<0){
            throw new NotEnoughMoneyException("lack of balance");
        }
        this.balance-=krw;
    }
}

package com.gon.coin.demotradingcoin.domain;


import com.gon.coin.demotradingcoin.config.Role;
import com.gon.coin.demotradingcoin.domain.banktransactions.BankTransaction;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private Role role;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;


    @OneToMany(mappedBy="member")
    private List<BankTransaction> bankTransactions=new ArrayList<>();

    public Member(String username) {
        this.username = username;
    }

    @Builder
    public Member(Long id, String username, String password,Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role=role;
    }

    public Member(String username, String password, Role role, Account account) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.account = account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void deposit(Double krw) {
        this.getAccount().deposit(krw);
    }

    public void drawal(Double krw){
        this.getAccount().drawal(krw);
    }
}

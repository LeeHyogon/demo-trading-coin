package com.gon.coin.demotradingcoin.domain;


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

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;


    @OneToMany(mappedBy="member")
    private List<BankTrade> trades=new ArrayList<>();

    public Member(String username) {
        this.username = username;
    }

    @Builder
    public Member(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Member(String username, String password, Account account) {
        this.username=username;
        this.password=password;
        this.account=account;
    }
}

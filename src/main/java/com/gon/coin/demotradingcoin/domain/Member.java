package com.gon.coin.demotradingcoin.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String password;

    @Embedded
    private Address address;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account;


    public Member(String username) {
        this.username = username;
    }

    @Builder
    public Member(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}

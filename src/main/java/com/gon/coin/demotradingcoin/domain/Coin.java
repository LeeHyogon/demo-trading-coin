package com.gon.coin.demotradingcoin.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Coin {
    @Id
    @GeneratedValue
    @Column(name = "coin_id")
    private Long id;

    String name;
    int price;
    String icon_url;


    public Coin(String name, int price, String icon_url) {
        this.name=name;
        this.price=price;
        this.icon_url=icon_url;
    }
}

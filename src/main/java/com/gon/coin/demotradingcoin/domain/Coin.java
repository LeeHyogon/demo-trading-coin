package com.gon.coin.demotradingcoin.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Coin {
    @Id
    @GeneratedValue
    @Column(name = "coin_id")
    private Long id;

    String name;

    int price;

    String icon_url;



}

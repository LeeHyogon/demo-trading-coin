package com.gon.coin.demotradingcoin.repository;

import com.gon.coin.demotradingcoin.domain.Coin;
import com.gon.coin.demotradingcoin.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface CoinRepository extends JpaRepository<Coin,Long> {

    @Query("select c from Coin c" +
            " join fetch c.member m" +
            " where m.username= :username")
    List<Coin> haveCoinsFind(@Param("username")String username);

    @Query("select c from Coin c" +
            " join fetch c.member m" +
            " where m.username= :username" +
            " and c.market= :market")
    Optional<Coin> haveCoinFind(@Param("username")String username,@Param("market") String market);
}

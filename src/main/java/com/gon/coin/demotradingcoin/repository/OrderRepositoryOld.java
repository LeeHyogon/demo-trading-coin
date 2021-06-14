package com.gon.coin.demotradingcoin.repository;

import com.gon.coin.demotradingcoin.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryOld {
    private final EntityManager em;

    public List<Order> findByTest(String market,Double transactionPrice){
        return em.createQuery("select o from Order o where o.market= :market" +
                " and o.transactionPrice= :transactionPrice",Order.class)
                .setParameter("market",market)
                .setParameter("transactionPrice",transactionPrice)
                .getResultList();
    }
}

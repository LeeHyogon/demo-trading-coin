package com.gon.coin.demotradingcoin.repository;

import com.gon.coin.demotradingcoin.domain.order.Order;
import com.gon.coin.demotradingcoin.domain.order.OrderStatus;
import com.gon.coin.demotradingcoin.domain.order.OrderTradeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByMarketAndTransactionPrice(String market,Double transactionPrice);
    @Query("select o from Order o")
    List<Order> findPlz();

    List<Order> findByMarketAndTransactionPriceAndTradeStatusAndStatus(String market, Double transactionPrice, OrderTradeStatus tradeStatus, OrderStatus status);
}

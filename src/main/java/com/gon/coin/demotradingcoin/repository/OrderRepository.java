package com.gon.coin.demotradingcoin.repository;

import com.gon.coin.demotradingcoin.domain.order.Order;
import com.gon.coin.demotradingcoin.domain.order.OrderStatus;
import com.gon.coin.demotradingcoin.domain.order.OrderTradeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByMarketAndTransactionPrice(String market, Double transactionPrice);

    @Query("select o from Order o where o.market= :market" +
            " and o.transactionPrice= :transactionPrice" +
            " and o.tradeStatus= :tradeStatus" +
            " and o.status= :status" +
            " and o.tradingVolume >= :tradingVolume")
    List<Order> TradeFind(@Param("market") String market,
                        @Param("transactionPrice") Double price,
                        @Param("tradeStatus") OrderTradeStatus tradeStatus,
                        @Param("status") OrderStatus status,
                        @Param("tradingVolume") Double tradingVolume);

    @Query("select o from Order o where o.market= :market" +
            " and o.transactionPrice= :transactionPrice" +
            " and o.tradeStatus= :tradeStatus" +
            " and o.status= :status" +
            " and o.tradingVolume >= :tradingVolume" +
            " order by o.createdDate asc")
    List<Order> TradeFindOrderAscTime(@Param("market") String market,
                          @Param("transactionPrice") Double price,
                          @Param("tradeStatus") OrderTradeStatus tradeStatus,
                          @Param("status") OrderStatus status,
                          @Param("tradingVolume") Double tradingVolume);


    List<Order> findByMarketAndTransactionPriceAndTradeStatusAndStatus(String market, Double transactionPrice, OrderTradeStatus tradeStatus, OrderStatus status);

}

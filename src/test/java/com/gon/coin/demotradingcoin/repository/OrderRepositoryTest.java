package com.gon.coin.demotradingcoin.repository;

import com.gon.coin.demotradingcoin.domain.member.Member;
import com.gon.coin.demotradingcoin.domain.order.Order;
import com.gon.coin.demotradingcoin.domain.order.OrderStatus;
import com.gon.coin.demotradingcoin.domain.order.OrderTradeStatus;
import org.aspectj.weaver.ast.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderRepositoryOld orderRepositoryOld;
    @Test
    public void findBy1() {
        //given
        String market="KRW-BTC";
        Double transactionPrice= 10000.0;
        Order order1=new Order(market,transactionPrice);
        Order order2=new Order(market,transactionPrice);
        orderRepository.save(order1);
        orderRepository.save(order2);
        //when
        List<Order> orderList=orderRepository.findByMarketAndTransactionPrice(market,transactionPrice);
        //then
        assertEquals(orderList.size(),2);
        for( Order order : orderList){
            assertEquals(order.getMarket(),"KRW-BTC");
            assertEquals(order.getTransactionPrice(),Double.valueOf(10000));
        }
    }
    @Test
    public void findBy2() throws Exception {
        //given
        String market="KRW-BTC";
        Double transactionPrice=Double.valueOf(10000);
        Member member =new Member();
        OrderTradeStatus tradeStatus=OrderTradeStatus.SELL;
        OrderStatus orderStatus=OrderStatus.WAIT;
        Order order1=new Order(market,orderStatus,tradeStatus,transactionPrice);
        Order order2=new Order(market,orderStatus,tradeStatus,transactionPrice);
        orderRepository.save(order1);
        orderRepository.save(order2);
        List<Order> orderList=
                orderRepository.findByMarketAndTransactionPriceAndTradeStatusAndStatus(market,transactionPrice,tradeStatus,orderStatus);
        assertEquals(orderList.size(),2);
        //then
        for( Order order : orderList){
            assertEquals(order.getMarket(),"KRW-BTC");
            assertEquals(order.getTransactionPrice(),Double.valueOf(10000));
            assertEquals(order.getTradeStatus(),OrderTradeStatus.SELL);
            assertEquals(order.getStatus(),OrderStatus.WAIT);
        }
    }
    @Test
    public void findBy3() throws Exception {
        //given
        String market="KRW-BTC";
        Double transactionPrice=Double.valueOf(10000);
        OrderTradeStatus tradeStatus=OrderTradeStatus.SELL;
        OrderStatus orderStatus=OrderStatus.WAIT;
        Double tradingVolume=0.3;
        Order order1=new Order(market,orderStatus,tradeStatus,transactionPrice,tradingVolume);
        Order order2=new Order(market,orderStatus,tradeStatus,transactionPrice,tradingVolume);
        orderRepository.save(order1);
        orderRepository.save(order2);
//        List<Order> orderList=
//        List<Order> orderList=
//                orderRepository.findByTradeList(market,transactionPrice,tradeStatus,orderStatus,0.4);
//        //then
//        assertEquals(orderList.size(),2);
//        for( Order order : orderList){
//            assertEquals(order.getMarket(),"KRW-BTC");
//            assertEquals(order.getTransactionPrice(),Double.valueOf(10000));
//            assertEquals(order.getTradeStatus(),OrderTradeStatus.SELL);
//            assertEquals(order.getStatus(),OrderStatus.WAIT);
//            assertEquals(order.getTradingVolume(),Double.valueOf(0.3));
//            assertEquals(order.getTradingVolume(),Double.valueOf(0.4));
//        }
    }

    @Test
    public void errorTest() throws Exception {
        //given
        orderRepository.findPlz();
        //when

        //then
    }


}
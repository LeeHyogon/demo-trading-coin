package com.gon.coin.demotradingcoin.repository;

import com.gon.coin.demotradingcoin.domain.order.Order;
import com.gon.coin.demotradingcoin.domain.order.OrderStatus;
import com.gon.coin.demotradingcoin.domain.order.OrderTradeStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class OrderRepositoryTest {
    @Autowired
    OrderRepository orderRepository;
    @Test
    public void findBy1() throws Exception {
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
    public void 주문대기시간정렬확인() throws Exception {
        //given
        String market="KRW-BTC";
        Double transactionPrice=Double.valueOf(10000);
        OrderTradeStatus tradeStatus=OrderTradeStatus.SELL;
        OrderStatus orderStatus=OrderStatus.WAIT;
        Double tradingVolume=0.3;
        //생성자 파라미터 순서 조심

        Order order1=new Order(market,orderStatus,tradeStatus,tradingVolume,transactionPrice);
        orderRepository.save(order1);
        Thread.sleep(1000);
        Order order2=new Order(market,orderStatus,tradeStatus,tradingVolume,transactionPrice);
        orderRepository.save(order2);
        List<Order> orderList=
                orderRepository.TradeFindOrderAscTime(market,transactionPrice,tradeStatus,orderStatus,0.2);
        //then
        assertEquals(orderList.size(),2);
        for( Order order : orderList){
            assertEquals(order.getMarket(),"KRW-BTC");
            assertEquals(order.getTransactionPrice(),Double.valueOf(10000));
            assertEquals(order.getTradeStatus(),OrderTradeStatus.SELL);
            assertEquals(order.getStatus(),OrderStatus.WAIT);
            System.out.println(order.getCreatedDate()+"----------------!!!!!!!!!!!!!!_--------------");
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
        //생성자 파라미터 순서 조심
        Order order1=new Order(market,orderStatus,tradeStatus,tradingVolume,transactionPrice);
        Order order2=new Order(market,orderStatus,tradeStatus,tradingVolume,transactionPrice);
        orderRepository.save(order1);
        orderRepository.save(order2);

        List<Order> orderList=
                orderRepository.TradeFind(market,transactionPrice,tradeStatus,orderStatus,0.4);
        //then
        assertEquals(orderList.size(),2);
        for( Order order : orderList){
            assertEquals(order.getMarket(),"KRW-BTC");
            assertEquals(order.getTransactionPrice(),Double.valueOf(10000));
            assertEquals(order.getTradeStatus(),OrderTradeStatus.SELL);
            assertEquals(order.getStatus(),OrderStatus.WAIT);
            assertEquals(order.getTradingVolume(),Double.valueOf(0.3));
//            assertEquals(order.getTradingVolume(),Double.valueOf(0.4));
        }
    }
}

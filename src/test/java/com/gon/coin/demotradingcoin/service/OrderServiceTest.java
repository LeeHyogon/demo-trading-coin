package com.gon.coin.demotradingcoin.service;

import com.gon.coin.demotradingcoin.domain.Coin;
import com.gon.coin.demotradingcoin.domain.member.Member;
import com.gon.coin.demotradingcoin.domain.order.Order;
import com.gon.coin.demotradingcoin.domain.order.OrderStatus;
import com.gon.coin.demotradingcoin.domain.order.OrderTradeStatus;
import com.gon.coin.demotradingcoin.repository.CoinRepository;
import com.gon.coin.demotradingcoin.repository.MemberRepository;
import com.gon.coin.demotradingcoin.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired OrderRepository orderRepository;
    @Autowired MemberRepository memberRepository;
    @Autowired CoinRepository coinRepository;
    @Autowired UpbitCoinService upbitCoinService;
    @Autowired OrderService orderService;
    @Test
    public void 지정가매수_매도주문없는경우() throws Exception {
        //given
        String username1="memberA";
        String market="KRW-BTC";
        String username2="memberB";

        Double transactionPrice=10000.0;
        Double tradingVolume=0.3;
        Member member1=new Member(username1);
        Member member2=new Member(username2);
        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        Long ret = orderService.pendingBuy(member1.getId(), market, transactionPrice, tradingVolume);
        //then
        assertEquals(ret,Long.valueOf(-1));
    }
    @Test
    public void 지정가매수_매도주문_코인보유한경우() throws Exception {
        //given
        String username1="memberA";
        String market="KRW-BTC";
        String username2="memberB";
        Double transactionPrice=10000.0;
        Double tradingVolume=0.3;
        Member member1=new Member(username1);
        Member member2=new Member(username2);
        memberRepository.save(member1);
        Coin haveCoin=new Coin().builder()
                .member(member1)
                .market(market)
                .volume(1.0)
                .averagePrice(11000.0)
                .totalKrw(11000.0)
                .build();
        coinRepository.save(haveCoin);
        memberRepository.save(member2);
        OrderStatus orderStatus=OrderStatus.WAIT;
        OrderTradeStatus orderTradeStatus=OrderTradeStatus.BUY;
        Order sellOrder = Order.createOrder(member2, market, tradingVolume, transactionPrice, orderStatus, OrderTradeStatus.SELL);
        orderRepository.save(sellOrder);
        //when
        Long orderId = orderService.pendingBuy(member1.getId(), market, transactionPrice, tradingVolume);
        Order comOrder=orderRepository.findById(orderId).get();
        assertEquals(comOrder.getStatus(),OrderStatus.COMPLETE);
        assertEquals(sellOrder.getStatus(),OrderStatus.COMPLETE);
        Coin coin = coinRepository.haveCoinFind("memberA", market).get();
        assertEquals(coin.getMarket(),market);
        assertEquals(coin.getMember().getUsername(),"memberA");
        System.out.println(coin.getAveragePrice());
        System.out.println(coin.getTotalKrw());
        System.out.println(coin.getVolume());
    }

    @Test
    public void 지정가매수_매도주문_코인없는경우() throws Exception {
        //given
        String username1="memberA";
        String market="KRW-BTC";
        String username2="memberB";
        Double transactionPrice=10000.0;
        Double tradingVolume=0.3;
        Member member1=new Member(username1);
        Member member2=new Member(username2);
        memberRepository.save(member1);
        memberRepository.save(member2);
        OrderStatus orderStatus=OrderStatus.WAIT;
        OrderTradeStatus orderTradeStatus=OrderTradeStatus.BUY;
        Order sellOrder = Order.createOrder(member2, market, tradingVolume, transactionPrice, orderStatus, OrderTradeStatus.SELL);
        orderRepository.save(sellOrder);
        //when
        Long orderId = orderService.pendingBuy(member1.getId(), market, transactionPrice, tradingVolume);
        Order comOrder=orderRepository.findById(orderId).get();
        assertEquals(comOrder.getStatus(),OrderStatus.COMPLETE);
        Coin coin = coinRepository.haveCoinFind("memberA", market).get();
        assertEquals(coin.getMarket(),market);
        assertEquals(coin.getMember().getUsername(),"memberA");
        System.out.println(coin.getKoreanName());
        System.out.println(coin.getEnglishName());
        System.out.println(coin.getAveragePrice());
        System.out.println(coin.getTotalKrw());
        System.out.println(coin.getVolume());
    }
}
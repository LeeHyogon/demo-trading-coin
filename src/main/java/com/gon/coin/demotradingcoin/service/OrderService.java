package com.gon.coin.demotradingcoin.service;


import com.gon.coin.demotradingcoin.domain.member.Member;
import com.gon.coin.demotradingcoin.domain.order.Order;
import com.gon.coin.demotradingcoin.domain.order.OrderStatus;
import com.gon.coin.demotradingcoin.domain.order.OrderTradeStatus;
import com.gon.coin.demotradingcoin.repository.MemberRepository;
import com.gon.coin.demotradingcoin.repository.OrderRepository;
import com.gon.coin.demotradingcoin.repository.OrderRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final OrderRepositoryOld orderRepositoryOld;

    @Transactional
    public Long buy(Long memberId, String market,Double transactionPrice,Double tradingVolume) {
        Member member=memberRepository.findById(memberId).get();
        OrderStatus orderStatus=OrderStatus.WAIT;
        OrderTradeStatus orderTradeStatus=OrderTradeStatus.BUY;
        List<Order> orders=orderRepository.findByMarketAndTransactionPrice(market, transactionPrice);
        Order order=Order.createOrder(member,market,tradingVolume,transactionPrice,orderStatus,orderTradeStatus);
        orderRepository.save(order);
        return order.getId();
    }
    @Transactional
    public Long sell(Long memberId, String market,Double transactionPrice,Double tradingVolume) {
        Member member=memberRepository.findById(memberId).get();
        OrderStatus orderStatus=OrderStatus.WAIT;
        OrderTradeStatus orderTradeStatus=OrderTradeStatus.SELL;
        Order order=Order.createOrder(member,market,tradingVolume,transactionPrice,orderStatus,orderTradeStatus);
        orderRepository.save(order);
        return order.getId();
    }




}

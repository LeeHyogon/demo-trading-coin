package com.gon.coin.demotradingcoin.service;


import com.gon.coin.demotradingcoin.domain.Coin;
import com.gon.coin.demotradingcoin.domain.member.Member;
import com.gon.coin.demotradingcoin.domain.order.Order;
import com.gon.coin.demotradingcoin.domain.order.OrderStatus;
import com.gon.coin.demotradingcoin.domain.order.OrderTradeStatus;
import com.gon.coin.demotradingcoin.repository.CoinRepository;
import com.gon.coin.demotradingcoin.repository.MemberRepository;
import com.gon.coin.demotradingcoin.repository.OrderRepository;
import com.gon.coin.demotradingcoin.repository.OrderRepositoryOld;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final OrderRepositoryOld orderRepositoryOld;
    private final CoinRepository coinRepository;
    private final UpbitCoinService upbitCoinService;
    //transactionPrice: 매수단가 tradingVolume:매수량
    @Transactional
    public Long pendingBuy(Long memberId, String market,Double transactionPrice,Double tradingVolume) {
        Member member=memberRepository.findById(memberId).get();
        String username=member.getUsername();

        OrderStatus orderStatus=OrderStatus.WAIT;
        OrderTradeStatus orderTradeStatus=OrderTradeStatus.BUY;
        Order newOrder = Order.createOrder(member, market, tradingVolume, transactionPrice, orderStatus, orderTradeStatus);
        orderRepository.save(newOrder);

        List<Order> list=orderRepository.TradeFindOrderAscTime(market,transactionPrice,OrderTradeStatus.SELL,OrderStatus.WAIT,tradingVolume);
        //바로 매수주문이 팔릴 수 있을지 확인.
        if(!list.isEmpty()){
            //가장 먼저 매도주문한것 불러오기
            Order sellOrder=list.get(0);
            //주문 상태는 완료로 변경.
            sellOrder.statusComplete();
            newOrder.statusComplete();
            //현재 멤버가 가지고있는 특정코인정보가 필요.
            Coin coin = coinRepository.haveCoinFind(username, market).orElse(null);
            //해당 코인을 보유하지 않은 경우.
            if(coin==null){
                List<String> names=upbitCoinService.getCoinNames(market);
                String koreanName=names.get(0);
                String englishName=names.get(1);
                Double volume=tradingVolume;
                Double averagePrice=transactionPrice;
                Double totalKrw=transactionPrice*tradingVolume;
                Coin newCoin=new Coin().builder()
                        .market(market)
                        .member(member)
                        .build();
            }else{
                //coin.update(tradingVolume,transactionPrice);
            }

        }

        return newOrder.getId();
    }
    @Transactional
    public Long pendingSell(Long memberId, String market,Double transactionPrice,Double tradingVolume) {
        Member member=memberRepository.findById(memberId).get();
        OrderStatus orderStatus=OrderStatus.WAIT;
        OrderTradeStatus orderTradeStatus=OrderTradeStatus.SELL;
        Order order=Order.createOrder(member,market,tradingVolume,transactionPrice,orderStatus,orderTradeStatus);
        orderRepository.save(order);
        return order.getId();
    }




}

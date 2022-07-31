package com.gon.coin.demotradingcoin.service;


import com.gon.coin.demotradingcoin.domain.Coin;
import com.gon.coin.demotradingcoin.domain.member.Member;
import com.gon.coin.demotradingcoin.domain.order.Order;
import com.gon.coin.demotradingcoin.domain.order.OrderStatus;
import com.gon.coin.demotradingcoin.domain.order.OrderTradeStatus;
import com.gon.coin.demotradingcoin.repository.CoinRepository;
import com.gon.coin.demotradingcoin.repository.MemberRepository;
import com.gon.coin.demotradingcoin.repository.OrderRepository;
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
    private final CoinRepository coinRepository;
    private final UpbitCoinService upbitCoinService;
    //transactionPrice: 매수단가 tradingVolume:매수량 //일부체결은 일단 제외
    @Transactional
    public Long pendingBuy(Long memberId, String market,Double transactionPrice,Double tradingVolume) {
        Member member=memberRepository.findById(memberId).get();
        String username=member.getUsername();
        OrderStatus orderStatus=OrderStatus.WAIT;
        OrderTradeStatus orderTradeStatus=OrderTradeStatus.BUY;
        Order newOrder = Order.createOrder(member, market, tradingVolume, transactionPrice, orderStatus, orderTradeStatus);
        orderRepository.save(newOrder);
        List<Order> list=orderRepository.TradeFindOrderAscTime(market,transactionPrice,OrderTradeStatus.SELL,OrderStatus.WAIT);
        //바로 매수주문이 팔릴 수 있을지 확인.
        if(!list.isEmpty()){
            //가장 먼저 매도주문한것 불러오기
            Order sellOrder=list.get(0);
            Double sellOrderTradingVolume = sellOrder.getTradingVolume();
            //매수 수량보다 매도 수량이 많은 경우.
            if(sellOrderTradingVolume>tradingVolume){
                //매수 주문 상태는 완료로 변경.
                sellOrder.statusComplete();
                newOrder.statusComplete();
                // ---미체결 일부 매도주문 재생성 로직 ---//
                sellOrder.partContract(tradingVolume);
                Member sellMember=sellOrder.getMember();
                Double newSellTradingVolume=sellOrderTradingVolume-tradingVolume;
                Order newSellOrder = Order.createOrder(sellMember, market, newSellTradingVolume, transactionPrice, OrderStatus.WAIT, OrderTradeStatus.SELL);
                orderRepository.save(newSellOrder);
                //매도주문 한 회원 보유코인 정보 변경.
                Coin sellMemberCoin=coinRepository.haveCoinFind(sellMember.getUsername(),market).get();
                sellMemberCoin.sellUpdate(tradingVolume,transactionPrice);
                //현재 멤버가 가지고있는 특정코인정보가 필요.
                Optional<Coin> coin = coinRepository.haveCoinFind(username, market);
                //보유한경우
                if (coin.isPresent()) {
                    coin.get().buyUpdate(tradingVolume, transactionPrice);
                }
                //해당 코인을 보유하지 않은 경우.
                else {
                    List<String> names = upbitCoinService.getCoinNames(market);
                    String koreanName = names.get(0);
                    String englishName = names.get(1);
                    Double volume = tradingVolume;
                    Double averagePrice = transactionPrice;
                    Double totalKrw = transactionPrice * tradingVolume;
                    Coin newCoin = new Coin().builder()
                            .member(member)
                            .market(market)
                            .koreanName(koreanName)
                            .englishName(englishName)
                            .volume(volume)
                            .averagePrice(averagePrice)
                            .totalKrw(totalKrw)
                            .build();
                    coinRepository.save(newCoin);
                }
            }
            else if(sellOrderTradingVolume<tradingVolume){
                //매수 주문 상태는 완료로 변경.
                sellOrder.statusComplete();
                newOrder.statusComplete();
                // ---미체결 일부 매수주문 재생성 로직 ---//
                newOrder.partContract(tradingVolume);
                Member buyMember=newOrder.getMember();
                Double newBuyTradingVolume=tradingVolume-sellOrderTradingVolume;
                Order newBuyOrder = Order.createOrder(buyMember, market, newBuyTradingVolume, transactionPrice, OrderStatus.WAIT, OrderTradeStatus.BUY);
                orderRepository.save(newBuyOrder);
                //매도주문 한 회원 보유코인 정보 변경.
                Member sellMember=sellOrder.getMember();
                Coin sellMemberCoin=coinRepository.haveCoinFind(sellMember.getUsername(),market).get();
                sellMemberCoin.sellUpdate(sellOrderTradingVolume,transactionPrice);
                if(sellMemberCoin.getVolume()==0.0){
                    coinRepository.delete(sellMemberCoin);
                }
                //현재 멤버가 가지고있는 특정코인정보가 필요.
                Optional<Coin> coin = coinRepository.haveCoinFind(username, market);
                //보유한경우
                if (coin.isPresent()) {
                    coin.get().buyUpdate(newBuyTradingVolume, transactionPrice);
                }
                //해당 코인을 보유하지 않은 경우.
                else {
                    List<String> names = upbitCoinService.getCoinNames(market);
                    String koreanName = names.get(0);
                    String englishName = names.get(1);
                    Double volume = newBuyTradingVolume;
                    Double averagePrice = transactionPrice;
                    Double totalKrw = transactionPrice * newBuyTradingVolume;
                    Coin newCoin = new Coin().builder()
                            .member(member)
                            .market(market)
                            .koreanName(koreanName)
                            .englishName(englishName)
                            .volume(volume)
                            .averagePrice(averagePrice)
                            .totalKrw(totalKrw)
                            .build();
                    coinRepository.save(newCoin);
                }
            }
            else if(sellOrderTradingVolume==tradingVolume) {
                //매수매도 주문 상태는 완료로 변경.
                sellOrder.statusComplete();
                newOrder.statusComplete();
                //매도주문 한 회원 보유코인 정보 변경.
                Member sellMember=sellOrder.getMember();
                Coin sellMemberCoin=coinRepository.haveCoinFind(sellMember.getUsername(),market).get();
                sellMemberCoin.sellUpdate(sellOrderTradingVolume,transactionPrice);
                if(sellMemberCoin.getVolume()==0.0){
                    coinRepository.delete(sellMemberCoin);
                }

                //현재 멤버가 가지고있는 특정코인정보가 필요.
                Optional<Coin> coin = coinRepository.haveCoinFind(username, market);

                //보유한경우
                if (coin.isPresent()) {
                    coin.get().buyUpdate(tradingVolume, transactionPrice);
                }
                //해당 코인을 보유하지 않은 경우.
                else {
                    List<String> names = upbitCoinService.getCoinNames(market);
                    String koreanName = names.get(0);
                    String englishName = names.get(1);
                    Double volume = tradingVolume;
                    Double averagePrice = transactionPrice;
                    Double totalKrw = transactionPrice * tradingVolume;
                    Coin newCoin = new Coin().builder()
                            .member(member)
                            .market(market)
                            .koreanName(koreanName)
                            .englishName(englishName)
                            .volume(volume)
                            .averagePrice(averagePrice)
                            .totalKrw(totalKrw)
                            .build();
                    coinRepository.save(newCoin);
                }
            }
        }
        else{
            return Long.valueOf(-1);
        }
        return newOrder.getId();
    }



}

package com.gon.coin.demotradingcoin.domain.order;

import com.gon.coin.demotradingcoin.domain.member.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime excutionTime; //체결시간

    //마켓 코인
    private String market;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [CANCEL,COMPLETE,WAIT]

    @Enumerated(EnumType.STRING)
    private OrderTradeStatus tradeStatus; //주문상태 [BUY,SELL]


    //거래수량
    private Double tradingVolume;

    //거래단가
    private Double transactionPrice;

    //주문시간
    @CreatedDate
    private LocalDateTime createdDate;


    public static Order createOrder(Member member, String market, Double tradingVolume, Double transactionPrice, OrderStatus orderStatus, OrderTradeStatus orderTradeStatus) {
        Order order=new Order().builder()
                .member(member)
                .market(market)
                .tradingVolume(tradingVolume)
                .transactionPrice(transactionPrice)
                .status(orderStatus)
                .tradeStatus(orderTradeStatus)
                .build();
        return order;
    }

    @Builder
    public Order(Member member, String market, OrderStatus status, OrderTradeStatus tradeStatus, Double tradingVolume, Double transactionPrice) {
        this.member = member;
        this.market = market;
        this.status = status;
        this.tradeStatus = tradeStatus;
        this.tradingVolume = tradingVolume;
        this.transactionPrice = transactionPrice;
    }

    //==repositoryTest 사용 생성자 ==//

    public Order(String market, Double transactionPrice) {
        this.market = market;
        this.transactionPrice = transactionPrice;
    }

    public Order(String market, OrderStatus status, OrderTradeStatus tradeStatus, Double transactionPrice) {
        this.market = market;
        this.status = status;
        this.tradeStatus = tradeStatus;
        this.transactionPrice = transactionPrice;
    }
    public Order(String market, OrderStatus status, OrderTradeStatus tradeStatus, Double tradingVolume, Double transactionPrice) {
        this.market = market;
        this.status = status;
        this.tradeStatus = tradeStatus;
        this.tradingVolume = tradingVolume;
        this.transactionPrice = transactionPrice;
    }
}

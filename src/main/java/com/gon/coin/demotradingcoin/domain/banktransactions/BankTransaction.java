package com.gon.coin.demotradingcoin.domain.banktransactions;

import com.gon.coin.demotradingcoin.domain.BaseTimeEntity;
import com.gon.coin.demotradingcoin.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class BankTransaction extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "banktransaction_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    Double transferAmount;


    @Enumerated(EnumType.STRING)
    private BankTransactionStatus status; //DEPOSIT,DRAWAL


    @Builder
    public BankTransaction(Member member, Double transferAmount, BankTransactionStatus status) {
        this.member = member;
        this.transferAmount = transferAmount;
        this.status = status;
    }
}

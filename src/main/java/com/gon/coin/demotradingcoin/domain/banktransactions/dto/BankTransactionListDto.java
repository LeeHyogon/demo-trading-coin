package com.gon.coin.demotradingcoin.domain.banktransactions.dto;

import com.gon.coin.demotradingcoin.domain.BaseTimeEntity;
import com.gon.coin.demotradingcoin.domain.banktransactions.BankTransaction;
import com.gon.coin.demotradingcoin.domain.banktransactions.BankTransactionStatus;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
public class BankTransactionListDto  {

    Double transferAmount;

    @Enumerated(EnumType.STRING)
    private BankTransactionStatus status; //DEPOSIT,DRAWAL

    private LocalDateTime createdDate;

    @Builder
    public BankTransactionListDto(Double transferAmount, BankTransactionStatus status, LocalDateTime createdDate) {
        this.transferAmount = transferAmount;
        this.status = status;
        this.createdDate = createdDate;
    }

    public BankTransactionListDto(BankTransaction o) {
        transferAmount=o.getTransferAmount();
        status=o.getStatus();
        createdDate=o.getCreatedDate();
    }
}

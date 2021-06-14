package com.gon.coin.demotradingcoin.service;


import com.gon.coin.demotradingcoin.domain.banktransactions.BankTransaction;
import com.gon.coin.demotradingcoin.domain.banktransactions.BankTransactionStatus;
import com.gon.coin.demotradingcoin.domain.member.Member;
import com.gon.coin.demotradingcoin.repository.BankTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BankTransactionService {
    private final BankTransactionRepository bankTransactionRepository;

    @Transactional
    public Long addList(Member member, Double krw, BankTransactionStatus status){
        if(status==BankTransactionStatus.DEPOSIT){
            member.deposit(krw);
        }
        else if(status==BankTransactionStatus.DRAWAL){
            member.drawal(krw);
        }
        BankTransaction bankTransaction=new BankTransaction().builder()
                .member(member)
                .transferAmount(krw)
                .status(status)
                .build();
        bankTransactionRepository.save(bankTransaction);
        return member.getId();
    }

    public List<BankTransaction> findByUsername(String username) {
        return bankTransactionRepository.finByUsername(username);
    }
}

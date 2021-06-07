package com.gon.coin.demotradingcoin.repository;

import com.gon.coin.demotradingcoin.domain.banktransactions.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {

    @Query("SELECT b FROM BankTransaction b join fetch b.member m where m.username =:username")
    List<BankTransaction> finByUsername(@Param("username")String username);
}

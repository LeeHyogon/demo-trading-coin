package com.gon.coin.demotradingcoin.repository;

import com.gon.coin.demotradingcoin.domain.upbitcoin.DayItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayItemRepository extends JpaRepository<DayItem,Long> {
}

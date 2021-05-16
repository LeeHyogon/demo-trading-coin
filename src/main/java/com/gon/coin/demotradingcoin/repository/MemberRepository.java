package com.gon.coin.demotradingcoin.repository;

import com.gon.coin.demotradingcoin.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long> {

    List<Member> findByName(String name);
}


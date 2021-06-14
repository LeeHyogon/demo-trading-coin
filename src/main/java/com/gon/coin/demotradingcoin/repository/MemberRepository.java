package com.gon.coin.demotradingcoin.repository;

import com.gon.coin.demotradingcoin.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUsername(String username);

    @Query("select m from Member m where m.username= :username")
    List<Member> findUser(@Param("username") String username);
}


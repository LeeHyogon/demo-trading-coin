package com.gon.coin.demotradingcoin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gon.coin.demotradingcoin.config.Role;
import com.gon.coin.demotradingcoin.domain.Member;
import com.gon.coin.demotradingcoin.dto.MemberDto;
import com.gon.coin.demotradingcoin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    //회원 가입 simple
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }
    private void validateDuplicateMember(Member member) {
        //EXCEPTION, 실무에서는 이름에 unique제약조건 걸어줘야함.
        Optional<Member> findMembers=memberRepository.findByUsername(member.getUsername());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("already exist user");
        }
    }
    // 회원가입 security
    @Transactional
    public Long signUp(MemberDto memberDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        // password를 암호화 한 뒤 dp에 저장
        return memberRepository.save(memberDto.toEntity()).getId();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 로그인을 하기 위해 가입된 user정보를 조회하는 메서드
        Optional<Member> memberWrapper = memberRepository.findByUsername(username);
        Member member = memberWrapper.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if("admin".equals(username)){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        // 아이디, 비밀번호, 권한리스트를 매개변수로 User를 만들어 반환해준다.
        return new User(member.getUsername(), member.getPassword(), authorities);
    }

    //회원 전체 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findById(memberId).get();
    }
    public Member findByName(String username){
        return memberRepository.findByUsername(username).get();
    }

    public Optional<Member> currentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Optional<Member> member = memberRepository.findByUsername(user.getUsername());
        return member;
    }


//    @Transactional
//    public Long deposit(Member member,Double krw){
//        member.deposit(krw);
//        //memberRepository.save(member);
//        return member.getId();
//    }
//
//    @Transactional
//    public Long drawal(Member member,Double krw){
//        member.drawal(krw);
//        //memberRepository.save(member);
//        return member.getId();
//    }
}




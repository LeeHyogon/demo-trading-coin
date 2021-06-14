package com.gon.coin.demotradingcoin.domain.member.dto;

import com.gon.coin.demotradingcoin.config.Role;
import com.gon.coin.demotradingcoin.domain.member.Member;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class MemberDto {
    private Long id;
    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String username;
    @NotEmpty(message = "비밀 번호는 필수 입니다.")
    private String password;

    private Role role;

    public MemberDto(String username, String password) {
        this.username=username;
        this.password=password;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(username)
                .password(password)
                .role(Role.MEMBER)
                .build();
    }

}



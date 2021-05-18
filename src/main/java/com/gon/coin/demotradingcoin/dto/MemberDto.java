package com.gon.coin.demotradingcoin.dto;

import com.gon.coin.demotradingcoin.config.Role;
import com.gon.coin.demotradingcoin.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Getter
@Setter
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

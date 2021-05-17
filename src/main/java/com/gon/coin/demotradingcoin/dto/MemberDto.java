package com.gon.coin.demotradingcoin.dto;

import com.gon.coin.demotradingcoin.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
public class MemberDto {
    private Long id;
    @NotEmpty(message = "회원 이름은 필수 입니다.")
    private String username;
    @NotEmpty(message = "비밀 번호는 필수 입니다.")
    private String password;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(username)
                .password(password)
                .build();
    }

    @Builder
    public MemberDto(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}

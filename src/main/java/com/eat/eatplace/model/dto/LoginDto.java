package com.eat.eatplace.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * 로그인시 사용할 DTO
 */
public class LoginDto {
    @NotNull
    private String userId;

    @NotNull
    private String userPwd;
}

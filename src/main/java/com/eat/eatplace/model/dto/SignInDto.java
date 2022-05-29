package com.eat.eatplace.model.dto;

import javax.validation.constraints.NotNull;

public class SignInDto {
    @NotNull
    private String userId;

    @NotNull
    private String userPwd;
}

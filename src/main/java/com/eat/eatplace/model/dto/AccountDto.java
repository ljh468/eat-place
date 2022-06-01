package com.eat.eatplace.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountDto {
    /**
     * 아이디
     */
    private String userId;
    /**
     * 닉네임
     */
    private String nickName;
    /**
     * 비밀번호
     */
    private String userPwd;
    /**
     * 나이
     */
    private Long age;
    /**
     * 성별
     */
    private String gender;
    /**
     * 생년월일
     */
    private String birth;
    /**
     * 이메일
     */
    private String email;
}

package com.eat.eatplace.model.dto;

import com.eat.eatplace.model.type.Gender;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class AccountDto {
    /**
     * 유저 고유번호
     */
    private Long userSeq;
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
    private Gender gender;
    /**
     * 생년월일
     */
    private String birth;
    /**
     * 이메일
     */
    private String email;
}

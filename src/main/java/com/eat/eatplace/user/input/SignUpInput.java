package com.eat.eatplace.user.input;

import com.eat.eatplace.model.type.ProviderType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@RequiredArgsConstructor
public class SignUpInput {
    /**
     * 가입경로
     */
    private final ProviderType providerType;
    /**
     * 유저 아이디
     */
    private final String userId;
    /**
     * 유저 닉네임
     */
    private final String nickName;
    /**
     * 비밀번호
     */
    private final String userPwd;
    /**
     * 나이
     */
    private final Long age;
    /**
     * 성별
     */
    private final String gender;
    /**
     * 생년월일
     */
    private final String birth;
    /**
     * 이메일
     */
    private final String email;

    /**
     * 인가코드
     */
    private final String AuthorizationCode;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}

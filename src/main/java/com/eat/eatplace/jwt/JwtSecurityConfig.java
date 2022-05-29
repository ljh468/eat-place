package com.eat.eatplace.jwt;

import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * JwtFilter와 TokenProvider를 SecurityConfig에 적용하기 위한 클래스
 */
public class JwtSecurityConfig implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    private TokenProvider tokenProvider;

    public JwtSecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }


    @Override
    public void init(HttpSecurity builder) throws Exception {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 생성한 JwtFilter를 Security로직에 필터를 등록
        JwtFilter customfilter = new JwtFilter(tokenProvider);
        http.addFilterBefore(customfilter, UsernamePasswordAuthenticationFilter.class);
    }
}

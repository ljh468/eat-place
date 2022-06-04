package com.example.demo.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class SecurityUtil {

    /**
     * Authentication 객체를 꺼내와서 username을 반환
     * (JwtFilter의 doFilter가 실행될때 Authentication 객체가 채워짐)
     */
    public static Optional<String> getCurrentUsername(){

        // authentication 객체를 상수로
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            log.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String username =null;
        if(authentication.getPrincipal() instanceof UserDetails){
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if(authentication.getPrincipal() instanceof String){
            username = (String) authentication.getPrincipal();
        }
        return Optional.ofNullable(username);
    }

}

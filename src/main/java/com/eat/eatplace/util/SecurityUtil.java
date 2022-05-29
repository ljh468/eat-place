package com.eat.eatplace.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * Authentication 객체를 꺼내와서 userid 반환
 * (JwtFilter의 doFilter가 실행될때 Authentication 객체가 채워짐)
 */
public class SecurityUtil {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    public static Optional<String> getCurrentUserId(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null){
            logger.debug("인증정보가 없습니다.");
            return Optional.empty();
        }
        String userId = null;
        if(authentication.getPrincipal() instanceof UserDetails){
            UserDetails securityAccount = (UserDetails) authentication.getPrincipal();
            userId = securityAccount.getUsername();
        }else if(authentication.getPrincipal() instanceof String){
            userId = (String) authentication.getPrincipal();
        }
        return Optional.ofNullable(userId);
    }
}

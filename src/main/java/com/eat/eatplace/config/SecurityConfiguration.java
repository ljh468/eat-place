package com.eat.eatplace.config;

import com.eat.eatplace.jwt.JwtAccessDeniedHandler;
import com.eat.eatplace.jwt.JwtAuthenticationEntryPoint;
import com.eat.eatplace.jwt.JwtSecurityConfig;
import com.eat.eatplace.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
// preAuthorize 어노테이션을 메서드 단위로 추가하기 위해서 적용
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityConfiguration(  //생성자 주입
                            TokenProvider tokenProvider,
                            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
                            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web){
        web
                // 랜더링 관련 요청은 시큐리 모두 무시
                .ignoring()
                .antMatchers("/js/**"
                , "/images/**"
                , "/css/**"
                , "/common/**"
                , "/file/**"
                , "/favicon.ico"
                );
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 토큰방식을 사용하기 때문에 csrf방식은 사용안함
                .csrf().disable()
                // 예외를 handling할때 구현한 클래스를 추가해줌
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                // h2-console을 위한 설정을 추가
                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // HttpServletRequest 요청에 대 접근제한을 설정
                .and()
                .authorizeRequests()
                // "/user/signup" 요청허용
                .antMatchers("/user/signup").permitAll()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/api/authenticate").permitAll()
                // 토큰을 받기위한 로그인 API와 회원가입 API는 토큰이 없는 상태에서 요청이 들어오기 때문에 모두 허가
                // 나머지는 모두 인증을 받아야함
                .anyRequest().authenticated()

                // JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig 클래스도 적용
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
}

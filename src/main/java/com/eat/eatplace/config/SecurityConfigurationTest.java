//package com.eat.eatplace.config;
//
//import com.eat.eatplace.jwt.JwtAccessDeniedHandler;
//import com.eat.eatplace.jwt.JwtAuthenticationEntryPoint;
//import com.eat.eatplace.jwt.JwtSecurityConfig;
//import com.eat.eatplace.jwt.TokenProvider;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SecurityConfigurationTest extends WebSecurityConfiguration {
//
//    private final TokenProvider tokenProvider;
//    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
//
//    public SecurityConfigurationTest(  //생성자 주입
//                                       TokenProvider tokenProvider,
//                                       JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
//                                       JwtAccessDeniedHandler jwtAccessDeniedHandler
//    ) {
//        this.tokenProvider = tokenProvider;
//        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
//        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                // 예외를 handling할때 구현한 클래스를 추가해줌
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//                .accessDeniedHandler(jwtAccessDeniedHandler)
//
//                .and()
//                // h2-console을 위한 설정을 추가
//                .headers()
//                .frameOptions()
//                .sameOrigin()
//
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
//                // HttpServletReqeust 요청에 접근제한을 설정
//                .and()
//                .authorizeHttpRequests((authz) -> authz
//                        .antMatchers("/user/signup").permitAll()
//                        // 나머지는 인증 다 받아야함
//                        .anyRequest().authenticated()
//                )
//                .apply(new JwtSecurityConfig(tokenProvider));
//
//
//        return http.build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        // 랜더링 관련 요청은 시큐리 모두 무시
//        return (web) -> web.ignoring().antMatchers("/js/**"
//                , "/images/**"
//                , "/css/**"
//                , "/common/**"
//                , "/file/**"
//                , "/favicon.ico");
//    }
//}
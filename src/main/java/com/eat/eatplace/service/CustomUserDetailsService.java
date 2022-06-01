package com.eat.eatplace.service;

import com.eat.eatplace.model.Account;
import com.eat.eatplace.repository.AccountRepository;
import com.eat.eatplace.util.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Autowired
    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        return accountRepository.findOneWithAuthoritiesByUserId(userId)
                .map(user -> createAccount(userId, user))
                .orElseThrow(() -> new UsernameNotFoundException(userId + " -> 데이터베이스에서 찾을 수 없습니다."));
    }
    private User createAccount(String userId, Account account){
        if(account.getUseYn().equals(CommonConst.ANSWER_NO)){
            throw new RuntimeException(userId + " -> 사용하는 아이디가 아닙니다.");
        }
        // 유저가 사용가능 하다면
        List<GrantedAuthority> grantedAuthorities = account.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
        // 아이디, 비밀번호, 권한정보를 가지고 User객체를 반환
        return new User(account.getUserId(), account.getUserPwd(), grantedAuthorities);
    }
}

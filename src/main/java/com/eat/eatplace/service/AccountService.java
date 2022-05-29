package com.eat.eatplace.service;

import com.eat.eatplace.model.Account;
import com.eat.eatplace.model.Authority;
import com.eat.eatplace.model.dto.AccountDto;
import com.eat.eatplace.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class AccountService {
    private AccountRepository accountRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    public Account getAccountInfo(AccountDto accountDto) {
        Long user_seq = accountDto.getUserSeq();
        Optional<Account> foundAccount = accountRepository.findById(user_seq);
        return foundAccount.get();
    }

    @Transactional
    public Account registerAccount(AccountDto accountDto) {
        Optional<Account> foundAccount = accountRepository.findByUserId(accountDto.getUserId());
        if (foundAccount.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다.");
        }
        // 유저정보가 없으면 권한 정보를 만듬
        Authority authority = Authority.builder()
                .authority_name("ROLE_USER")
                .build();
        // 권한정보와 유저정보를 담아서 저장
        Account account = Account.builder()
                .userId(accountDto.getUserId())
                .userPwd(passwordEncoder.encode(accountDto.getUserPwd()))
                .nickName(accountDto.getNickName())
                .birth(accountDto.getBirth())
                .email(accountDto.getEmail())
                .gender(accountDto.getGender())
                .age(accountDto.getAge())
                .joinDt(LocalDate.now())
                .authorities(Collections.singleton(authority))
                .build();

        return accountRepository.save(account);
    }
}

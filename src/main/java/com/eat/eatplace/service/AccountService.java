package com.eat.eatplace.service;

import com.eat.eatplace.kakao.KakaoResponse;
import com.eat.eatplace.kakao.KakaoService;
import com.eat.eatplace.model.Account;
import com.eat.eatplace.model.Authority;
import com.eat.eatplace.model.dto.AccountDto;
import com.eat.eatplace.model.type.Gender;
import com.eat.eatplace.model.type.ProviderType;
import com.eat.eatplace.repository.AccountRepository;
import com.eat.eatplace.user.input.SignUpInput;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private KakaoService kakaoService;

    @Transactional
    public Account getAccountInfo(String userId) {
        Optional<Account> foundAccount = accountRepository.findByUserId(userId);
        return foundAccount.get();
    }

    /**
     * 웹 버전의 회원가입
     */
    @Transactional
    public Account registerAccount(AccountDto accountDto) {
        Optional<Account> foundAccount = accountRepository.findByUserId(accountDto.getUserId());
        if (foundAccount.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다.");
        }
        // 유저정보가 없으면 권한 정보를 만듬
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();
        // 권한정보와 유저정보를 담아서 저장
        Account account = Account.builder()
                .userId(accountDto.getUserId())
                .userPwd(passwordEncoder.encode(accountDto.getUserPwd()))
                .nickName(accountDto.getNickName())
                .birth(accountDto.getBirth())
                .email(accountDto.getEmail())
                .gender(Gender.valueOf(accountDto.getGender()))
                .age(accountDto.getAge())
                .joinDt(LocalDate.now())
                .useYn("Y")
                .authorities(Collections.singleton(authority))
                .build();

        System.out.println("account = " + account.getUserSeq());
        return accountRepository.save(account);
    }

    /**
     * GraphQL 회원가입
     */
    public Account registerAccountGrahphql(SignUpInput input) {

        Account account = new Account();
        if(input.getProviderType().equals(ProviderType.LOCAL)){
            account.setUserId(input.getUserId());
            account.setNickName(input.getNickName());
            account.setAge(input.getAge());
            account.setEmail(input.getEmail());
            account.setGender(Gender.valueOf(input.getGender()));

        }
        /**
         * view단에서 앱인증키와 리다이렉션주소를 받아와야 한다.
         * 뷰가 분리된 환경에선 어떻게 보내는지 모르겠음
         * <a href="https://kauth.kakao.com/oauth/authorize?client_id=d953100edaca490adae7d0fde386bac5&
         * redirect_uri=http://localhost:8090/kakaoLogin.do&&response_type=code"></a>
         */
        else{
            // 엑세스 토큰 가져오기
            String accessToken = kakaoService.getAccessToken(input.getAuthorizationCode());

            // 카카오 로그인한 유저 정보 가져오기
            String kakaoUserInfo = kakaoService.getUserInfo(accessToken);

            Gson gsonUnderscore = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
            KakaoResponse response = gsonUnderscore.fromJson(kakaoUserInfo, new TypeToken<KakaoResponse>() {}.getType());
            account.setUserId(response.getKakaoAccount().getEmail());
            account.setNickName(response.getKakaoAccount().getName());
            account.setAge(response.getKakaoAccount().getAge_range());
            account.setEmail(response.getKakaoAccount().getEmail());
            account.setGender(Gender.valueOf(response.getKakaoAccount().getGender()));
        }

        // 저장
        accountRepository.save(account);
        return account;
    }
}

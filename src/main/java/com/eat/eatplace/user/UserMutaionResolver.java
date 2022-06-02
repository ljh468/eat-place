package com.eat.eatplace.user;

import com.eat.eatplace.model.Account;
import com.eat.eatplace.model.Token;
import com.eat.eatplace.service.AccountService;
import com.eat.eatplace.user.input.SignUpInput;
import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserMutaionResolver implements GraphQLMutationResolver {

    private static final String DUMMY_PASSWORD = "eatplace-password";

    private final AccountService accountService;

    @Autowired
    public UserMutaionResolver(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 회원가입 (완료시 Token을 내려줌)
     */
    public Token signUp(SignUpInput input){
        Account account = accountService.registerAccountGrahphql(input);
            return null;
    }

}

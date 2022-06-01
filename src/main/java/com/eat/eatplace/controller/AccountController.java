package com.eat.eatplace.controller;

import com.eat.eatplace.model.Account;
import com.eat.eatplace.model.dto.AccountDto;
import com.eat.eatplace.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/user")
public class AccountController {
    @Autowired
    private AccountService accountService;

    // 유저 회원가입 페이지
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/register")
    public ResponseEntity<Account> signup(@RequestBody AccountDto accountDto){
        return ResponseEntity.ok(accountService.registerAccount(accountDto));
    }

    // 유저 로그인 페이지
    @GetMapping("/signin")
    public String signin(){
        return "signin";
    }

    // 메인페이지
    @ResponseBody
    @GetMapping("/home")
    public Account home(@AuthenticationPrincipal User user, HttpServletRequest request, Model model){
        Account accountInfo = accountService.getAccountInfo(user.getUsername());
        return accountInfo;
    }


}

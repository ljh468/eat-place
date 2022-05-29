package com.eat.eatplace.controller;

import com.eat.eatplace.model.Account;
import com.eat.eatplace.model.dto.AccountDto;
import com.eat.eatplace.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
@NoArgsConstructor
@RequestMapping("/user")
public class AccountController {
    private AccountService accountService;

    // 유저 회원가입 페이지
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/register")
    public ResponseEntity<Account> signup(@Valid @RequestBody AccountDto accountDto){
        return ResponseEntity.ok(accountService.registerAccount(accountDto));
    }

    // 유저 로그인 페이지
    @GetMapping("/signin")
    public String signin(){
        return "signin";
    }

    // 메인페이지
    @GetMapping("/home")
    public String home(@RequestBody AccountDto accountDto, HttpServletRequest request, Model model){
        Account accountInfo = accountService.getAccountInfo(accountDto);
        model.addAttribute(accountInfo);
        return "home";
    }


}

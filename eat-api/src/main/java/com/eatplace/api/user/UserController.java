package com.eatplace.api.user;

import com.eatplace.user.Member;
import com.eatplace.user.MemberMongoRepository;
import com.eatplace.user.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

  private final MemberService memberService;

  private final MemberMongoRepository memberMongoRepository;

  @GetMapping("/transactionTest")
  public Member test(@RequestBody Member member) {
    try {
      memberService.bizLogic(member);
    } catch (RuntimeException e) {
      log.info(e.getMessage());
    }
    return memberMongoRepository.findByUsername("white paper");
  }
}

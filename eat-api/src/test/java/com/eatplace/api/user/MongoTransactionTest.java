package com.eatplace.api.user;

import com.eatplace.user.Member;
import com.eatplace.user.MemberMongoRepository;
import com.eatplace.user.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class MongoTransactionTest {

  @Autowired
  MemberMongoRepository memberMongoRepository;

  @Autowired
  MemberService memberService;

  @Test
  public void transaction_rollback_test() {
    long initialCount = memberMongoRepository.count();
    log.info("initialCount : " + initialCount);
    try {
      Member member = Member.builder().username("white paper").age(20).isDeleted(false).build();
      memberService.bizLogic(member);
    } catch (Exception exception) {
      log.info("transaction rollback");
    }
    long rollbackCount = memberMongoRepository.count();
    log.info("rollbackCount : " + rollbackCount);
    Assertions.assertThat(initialCount).isEqualTo(rollbackCount);
  }
}
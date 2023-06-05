package com.eatplace.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberMongoRepository memberMongoRepository;

  @Transactional
  public void bizLogic(Member member) {
    memberMongoRepository.save(member);
    if (true) {
      throw new RuntimeException();
    }
  }
}

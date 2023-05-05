package com.eatplace.user;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
// @Transactional
// @Rollback
public class MemberTest {

  @Autowired
  TeamRepository teamRepository;

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  EntityManager entityManager;

  @BeforeEach
  void setup() {
    for (int i = 0; i < 10; i++) {
      Member member = Member.builder().username("member" + i).age(i).isDeleted(false).build();
      memberRepository.save(member);

      Team team = Team.builder().name("team" + i).build();
      team.addMember(member);
      teamRepository.save(team);
    }

    entityManager.flush();
    entityManager.clear();
  }

  @AfterEach
  public void cleanAll() {
    memberRepository.deleteAll();
    teamRepository.deleteAll();
  }

  @Test
  @Transactional
  void findAll() {
    System.out.println(" =========================================================================== " );
    // given
    // when
    List<Team> all = teamRepository.findAll();
    // then
    for (Team team : all) {
      System.out.println("team = " + team + ", " + "members = " + team.getMembers());
    }
  }

  @Test
  @Transactional
  void findAllJoinFetch() {
    System.out.println(" =========================================================================== " );
    // given
    // when
    List<Team> all = teamRepository.findAllJoinFetch();
    // then
    for (Team team : all) {
      System.out.println("team = " + team + ", " + "members = " + team.getMembers());
    }
  }

  @Test
  @Transactional
  void findAllEntityGraph() {
    System.out.println(" =========================================================================== " );
    // given
    // when
    List<Team> all = teamRepository.findAllEntityGraph();
    // then
    for (Team team : all) {
      System.out.println("team = " + team + ", " + "members = " + team.getMembers());
    }
  }
}

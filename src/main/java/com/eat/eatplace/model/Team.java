package com.eat.eatplace.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "team")
public class Team {

    /**
     * 팀 고유번호
     */
    @Id
    @GeneratedValue
    @Column(name = "team_seq")
    private Long teamSeq;

    /**
     * 팀 아이디
     */
    @Column(name = "team_id")
    private String teamId;

    /**
     * 팀을 생성한 자
     */
    @Column(name = "team_generator_id")
    private String teamGeneratorId;

    /**
     * account와 양방향 매핑을 위해 team을 연관 매핑함
     * 연관관계의 주인이 아님 , 조회만 가능
     */
    @OneToMany(mappedBy = "team") // mappedBy : 연관관계의 주인이 자신을 team이라고 참조
    private List<Account> accounts = new ArrayList<>();

    /**
     * 조직 사용여부
     */
    private String useYn;
}

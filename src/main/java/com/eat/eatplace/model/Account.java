package com.eat.eatplace.model;

import com.eat.eatplace.model.type.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account")
public class Account {

    /**
     * 회원 고유번호
     */
    @Id
    @GeneratedValue
    @Column(name = "user_seq")
    private Long userSeq;
    /**
     * 아이디
     */
    @JsonIgnore
    @Column(name = "user_id")
    private String userId;
    /**
     * 닉네임
     */
    @Column(name = "nickname")
    private String nickName;
    /**
     * 비밀번호
     */
    @Column(name = "user_pwd")
    private String userPwd;
    /**
     * 나이
     */
    private Long age;
    /**
     * 성별
     */
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    /**
     * 생년월일
     */
    private String birth;
    /**
     * 가입일
     */
    private String email;
    /**
     * 가입일
     */
    @Column(name = "join_dt")
    private LocalDate joinDt;
    /**
     * 조직
     */
    @ManyToOne
    @JoinColumn(name = "team_seq")
    private Team team;
    /**
     * 권한
     */
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_seq", referencedColumnName = "user_seq")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
    /**
     * 유저 사용여부
     */
    private String useYn;
}

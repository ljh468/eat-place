package com.eatplace.user;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Member {

  @Id @GeneratedValue
  @EqualsAndHashCode.Include
  @Column(name = "MEMBER_ID")
  private Long id;

  private String username;

  private Integer age;

  private Boolean isDeleted;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TEAM_ID")
  private Team team;

  public void updateTeam(Team team) {
    this.team = team;
  }

  @Builder
  public Member(Long id, String username, Integer age, Boolean isDeleted, Team team) {
    this.id = id;
    this.username = username;
    this.age = age;
    this.isDeleted = isDeleted;
    this.team = team;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("id", id)
        .append("isDeleted", isDeleted)
        .toString();
  }
}

// package com.eatplace.user;
//
// import jakarta.persistence.*;
// import lombok.*;
// import org.apache.commons.lang3.builder.ToStringBuilder;
// import org.apache.commons.lang3.builder.ToStringStyle;
//
// import java.util.ArrayList;
// import java.util.List;
//
// @Getter
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
// @EqualsAndHashCode(onlyExplicitlyIncluded = true)
// // @Entity
// public class Team {
//
//   // @Id @GeneratedValue
//   @EqualsAndHashCode.Include
//   @Column(name = "TEAM_ID")
//   private Long id;
//
//   private String name;
//
//   @OneToMany(mappedBy = "team")
//   @Setter
//   private List<Member> members = new ArrayList<>();
//
//   public void addMember(Member member){
//     this.members.add(member);
//     member.updateTeam(this);
//   }
//
//   @Builder
//   public Team(Long id, String name, List<Member> members) {
//     this.id = id;
//     this.name = name;
//     if (members == null) {
//       this.members = new ArrayList<>();
//     } else {
//       this.members = members;
//     }
//   }
//
//   @Override
//   public String toString() {
//     return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
//         .append("id", id)
//         .append("name", name)
//         .toString();
//   }
// }

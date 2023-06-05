package com.eatplace.user;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Member {

  @Id
  @EqualsAndHashCode.Include
  private ObjectId id;

  private String username;

  private Integer age;

  private Boolean isDeleted;
  @Builder
  public Member(ObjectId id, String username, Integer age, Boolean isDeleted) {
    this.id = id;
    this.username = username;
    this.age = age;
    this.isDeleted = isDeleted;
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
        .append("id", id)
        .append("isDeleted", isDeleted)
        .toString();
  }
}

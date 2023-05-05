package com.eatplace.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Builder
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class User {

  @Id @GeneratedValue
  @EqualsAndHashCode.Include
  private Long id;

  private Boolean isDeleted;

  public User(Long id, Boolean isDeleted) {
    this.id = id;
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

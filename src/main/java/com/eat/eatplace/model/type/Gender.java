package com.eat.eatplace.model.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Gender {
    MALE("남자"),
    FEMALE("여자");

    private String description;
};

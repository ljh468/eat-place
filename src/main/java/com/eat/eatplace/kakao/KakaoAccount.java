package com.eat.eatplace.kakao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KakaoAccount {

    private KakaoProfile profile;
    private String name;
    private String email;
    private Long age_range;
    private String birthday;
    private String gender;
}

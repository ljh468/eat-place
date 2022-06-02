package com.eat.eatplace.kakao;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class KakaoResponse {
    private final Long id;

    private final String connectedAt;

    private final Map<String, String> properties;

    private final KakaoAccount kakaoAccount;
}

package com.eat.eatplace.kakao;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class KakaoProfile {
    private final String nickname;

    private final String thumbnailImageUrl;

    private final String profileImageUrl;

    private final Boolean isDefaultImage;
}

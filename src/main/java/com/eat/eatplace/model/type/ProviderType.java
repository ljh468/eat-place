package com.eat.eatplace.model.type;

public enum ProviderType {
    LOCAL("일반", "L"),
    KAKAO("카카오", "K");

    private final String name;
    private final String value;

    ProviderType(String name, String value) {
        this.name = name;
        this.value = value;
    }
}

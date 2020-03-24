package com.itechart.container.spring.users.entity.enums;

import java.util.AbstractMap;
import java.util.Map;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserEntityRole {

    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    private static final Map<String, UserEntityRole> MAP = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("ROLE_USER", ROLE_USER),
            new AbstractMap.SimpleEntry<>("ROLE_ADMIN", ROLE_ADMIN));

    private final String authority;

    public static UserEntityRole fromAuthority(String authority) {
        return MAP.get(authority);
    }

}

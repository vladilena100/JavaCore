package com.solutions.vasylieva.support;

import lombok.Getter;

@Getter
public enum EntityName {
    USER("User"),
    ROLE("Role");
    private final String name;

    EntityName(String name) {
        this.name = name;
    }
}

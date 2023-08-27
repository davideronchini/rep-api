package com.rep.api.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    BUSINESS_READ("business:read"),
    BUSINESS_UPDATE("business:update"),
    BUSINESS_CREATE("business:create"),
    BUSINESS_DELETE("business:delete"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete");

    @Getter
    private final String permission;
}

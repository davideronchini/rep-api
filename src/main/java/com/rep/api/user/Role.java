package com.rep.api.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rep.api.user.Permission.BUSINESS_CREATE;
import static com.rep.api.user.Permission.BUSINESS_DELETE;
import static com.rep.api.user.Permission.BUSINESS_READ;
import static com.rep.api.user.Permission.BUSINESS_UPDATE;

import static com.rep.api.user.Permission.ADMIN_CREATE;
import static com.rep.api.user.Permission.ADMIN_DELETE;
import static com.rep.api.user.Permission.ADMIN_READ;
import static com.rep.api.user.Permission.ADMIN_UPDATE;

@RequiredArgsConstructor
public enum Role {

    USER(Collections.emptySet()),

    BUSINESS(
            Set.of(
                    BUSINESS_READ,
                    BUSINESS_UPDATE,
                    BUSINESS_DELETE,
                    BUSINESS_CREATE
            )
    ),

    ADMIN(
            Set.of(
                    ADMIN_READ,
                    ADMIN_UPDATE,
                    ADMIN_DELETE,
                    ADMIN_CREATE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
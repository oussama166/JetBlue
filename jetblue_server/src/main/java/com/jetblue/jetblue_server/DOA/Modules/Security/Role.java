package com.jetblue.jetblue_server.DOA.Modules.Security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum Role {
    USER(Collections.EMPTY_SET),
    ADMIN(
            Set.of(
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_READ,
                    Permission.ADMIN_DELETE,
                    Permission.ADMIN_WRITE,
                    Permission.MANAGER_DELETE,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_WRITE,
                    Permission.MANAGER_READ
            )
    ),
    MANAGEMENT(
            Set.of(
                    Permission.MANAGER_DELETE,
                    Permission.MANAGER_UPDATE,
                    Permission.MANAGER_WRITE,
                    Permission.MANAGER_READ
            )
    );

    @Getter
    private final Set<Permission> permissions;

    public  List<SimpleGrantedAuthority> grantedAuthorities(){
        List<SimpleGrantedAuthority> authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .toList();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + name()));
        return authorities;
    }
}

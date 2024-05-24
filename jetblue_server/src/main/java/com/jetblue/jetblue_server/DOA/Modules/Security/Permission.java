package com.jetblue.jetblue_server.DOA.Modules.Security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

public enum Permission {

    ADMIN_READ("admin_read"),
    ADMIN_WRITE("admin_write"),
    ADMIN_DELETE("admin_delete"),
    ADMIN_UPDATE("admin_update"),

    USERS_READ("users_read"),
    USERS_WRITE("users_write"),
    USERS_DELETE("users_delete"),
    USERS_UPDATE("users_update"),

    MANAGER_READ("manager_read"),
    MANAGER_WRITE("manager_write"),
    MANAGER_DELETE("manager_delete"),
    MANAGER_UPDATE("manager_update");
    @Getter
    private final String permission;
}

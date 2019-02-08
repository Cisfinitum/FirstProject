package com.epam.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserRoleEnum {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN"),
    ANONYMOUS("ROLE_ANONYMOUS");

    private String Enumrole;
}

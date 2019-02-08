package com.epam.entity;

import com.epam.entity.enums.UserRoleEnum;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private String username;
    private String password;
    private UserRoleEnum role;
}

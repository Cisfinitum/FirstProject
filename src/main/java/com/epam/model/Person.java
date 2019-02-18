package com.epam.model;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
public class Person {
    private Integer id;
    private String email;
    private String password;
    private PersonRoleEnum role;

    public Person(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = PersonRoleEnum.valueOf(role);
    }
}

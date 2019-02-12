package com.epam.model;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
public class Person {
    private Integer id;
    private String name;
    private String password;
    private PersonRoleEnum role;
}

package com.epam.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class Person {
    private Integer id;
    private String name;
    private String password;
    private PersonRoleEnum role;


}

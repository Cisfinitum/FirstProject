package com.epam.model;

import lombok.*;

@Builder
@Data
public class Person {
    private Integer id;
    private String email;
    private String password;
    private PersonRoleEnum role;

    public Person(String email, String password, PersonRoleEnum role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Person(Integer id, String email, String password, PersonRoleEnum role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Person(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Person(PersonRoleEnum role) {
        this.role = role;
    }

}

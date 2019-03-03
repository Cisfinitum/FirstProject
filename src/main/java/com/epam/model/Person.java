package com.epam.model;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
@RequiredArgsConstructor
public class Person {
    private Integer id;
    private final String email;
    private final String password;
    private final PersonRoleEnum role;
    private final String phoneNumber;
    private final String firstName;
    private final String lastName;

    @Override
    public String toString() {
        return firstName + " " + lastName + ", \n " + phoneNumber + ", \n" + email;
    }
}

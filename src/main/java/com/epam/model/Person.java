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
}

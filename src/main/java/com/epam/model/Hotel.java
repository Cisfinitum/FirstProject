package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Hotel {
        private Integer id;
        private String name;
        private String city;
        private String country;
        private Integer numberOfStars;
}

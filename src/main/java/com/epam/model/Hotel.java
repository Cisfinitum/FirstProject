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

        public Hotel(String name, String city, String country, Integer numberOfStars) {
                this.name = name;
                this.city = city;
                this.country = country;
                this.numberOfStars = numberOfStars;
        }
}

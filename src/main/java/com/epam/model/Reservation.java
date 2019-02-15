package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Reservation {
    private Integer id;
    private Integer client_id;
    private Integer tourOffer_id;
    private Integer numberOfPeople;
    private String status;
    private Integer discount_id;
    private Integer totalPrice;
}

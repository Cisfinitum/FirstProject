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
    private Integer clientId;
    private Integer tourOfferId;
    private Integer numberOfPeople;
    private ReservationStatusEnum status;
    private Integer discountId;
    private Integer totalPrice;
}

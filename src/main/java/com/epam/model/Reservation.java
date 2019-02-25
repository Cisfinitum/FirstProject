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

    public Reservation(Integer clientId, Integer tourOfferId, Integer numberOfPeople, ReservationStatusEnum status, Integer discountId, Integer totalPrice) {
        this.clientId = clientId;
        this.tourOfferId = tourOfferId;
        this.numberOfPeople = numberOfPeople;
        this.status = status;
        this.discountId = discountId;
        this.totalPrice = totalPrice;
    }
}

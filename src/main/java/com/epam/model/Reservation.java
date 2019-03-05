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
    private ReservationArchiveStatusEnum archiveStatus;
    private Integer discount;
    private Integer totalPrice;

    public Reservation(Integer clientId, Integer tourOfferId, Integer numberOfPeople, ReservationStatusEnum status, Integer discount, Integer totalPrice, ReservationArchiveStatusEnum archiveStatus) {
        this.clientId = clientId;
        this.tourOfferId = tourOfferId;
        this.numberOfPeople = numberOfPeople;
        this.status = status;
        this.discount = discount;
        this.totalPrice = totalPrice;
        this.archiveStatus = archiveStatus;
    }
}

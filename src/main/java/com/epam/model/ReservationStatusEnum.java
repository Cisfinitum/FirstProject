package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum  ReservationStatusEnum {
    PAID("PAID"),
    UNPAID("UNPAID"),
    CANCELED("CANCELED");

    private String enumStatus;
}

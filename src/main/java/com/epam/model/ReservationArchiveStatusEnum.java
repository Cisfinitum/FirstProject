package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReservationArchiveStatusEnum {
    NEW("NEW"),
    ARCHIVED("ARCHIVED");

    private String enumArchiveStatus;
}

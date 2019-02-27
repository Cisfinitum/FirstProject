package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Data
public class TourOffer {
    private Integer id;
    private String tourType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer pricePerUnit;
    private Integer hotelId;
    private String description;
    private Integer discount;
}

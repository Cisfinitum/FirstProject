package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@AllArgsConstructor
@Builder
@Data
public class TourOffer {
    private Integer id;
    private String tourType;
    private Date startDate;
    private Date endDate;
    private Integer pricePerUnit;
    private Integer hotelId;
    private String description;
    private Integer discountId;
}

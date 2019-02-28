package com.epam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;

@AllArgsConstructor
@Builder
@Data
public class TourOffer {
    @NotNull
    @Size(min=1)
    private Integer id;
    @NotNull
    @Size(min=1, max=15)
    private String tourType;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    @Size(min=1)
    private Integer pricePerUnit;
    @NotNull
    @Size(min=1)
    private Integer hotelId;
    @NotNull
    @Size(min=1, max=100)
    private String description;
    @NotNull
    @Size(max = 100)
    private Integer discount;

    @Override
    public String toString() {
        Period period = Period.between(startDate, endDate);
        return tourType+ " tour, \n"+ period.getDays()+" days, \n";
    }
}

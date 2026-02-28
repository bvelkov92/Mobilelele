package com.mobilele.model.DTOs.Offer;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CurrentOfferDetails {

    @NotNull
    private Long id;

    @NotNull
    private Integer mileage;

    @NotNull
    private double price;

    @NotNull
    private String engineType;

    @NotNull
    private String transmissionType;

    @NotNull
    private LocalDate offerCreated;

    @NotNull
    private LocalDate offerModified;

    @NotNull
    private Long modelId;


}

package com.mobilele.model.DTOs.Offer;

import com.mobilele.model.enums.EngineTypeEnum;
import com.mobilele.model.enums.TransmissionTypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetAllOffers {
    @NotNull
    private Long id;
    @NotNull
    private String imageUrl;
    @NotNull
    private Integer mileage;
    @NotNull
    @Positive
    private double price;
    @NotNull
    private EngineTypeEnum engine;
    @NotNull
    private TransmissionTypeEnum transmission;
    @NotNull
    @Positive
    private Integer year;

    @NotNull
    private String brand;

    @NotNull
    private String model;

}

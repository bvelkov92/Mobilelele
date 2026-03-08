package com.mobilele.model.DTOs.Offer;

import com.mobilele.model.enums.EngineTypeEnum;
import com.mobilele.model.enums.TransmissionTypeEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class GetAllOffers {

    @NotNull
    private Long id;

    @NotBlank
    private String imageUrl;

    @NotNull
    @Min(1800)
    private Integer mileage;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal price;

    @NotNull
    private EngineTypeEnum engine;

    @NotNull
    private TransmissionTypeEnum transmission;

    @NotNull
    @Min(1800)
    private Integer year;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

}

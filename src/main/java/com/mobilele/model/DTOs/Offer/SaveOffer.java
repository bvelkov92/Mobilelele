package com.mobilele.model.DTOs.Offer;

import com.mobilele.model.enums.EngineTypeEnum;
import com.mobilele.model.enums.TypeOfVehicleEnums;
import com.mobilele.model.enums.TransmissionTypeEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SaveOffer {

    @Positive
    @NotNull
    private Long modelId;

    @NotNull
    private TypeOfVehicleEnums typeOfVehicle;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal price;

    @NotNull
    private EngineTypeEnum engine;

    @NotNull
    private TransmissionTypeEnum transmissionType;

    @NotNull
    @Min(1800)
    private Integer year;

    @Min(0)
    @NotNull
    private Integer mileage;

    @Size(min = 10, max = 512)
    @NotBlank
    private String description;

    @NotBlank
    private String imageUrl;

}

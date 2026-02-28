package com.mobilele.model.DTOs.Offer;

import com.mobilele.model.enums.EngineTypeEnum;
import com.mobilele.model.enums.TransmissionTypeEnum;
import com.mobilele.model.enums.TypeOfVehicleEnums;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class UpdateOffer {

    @NotNull
    private Long modelId;

    @NotNull
    private Long id;

    @NotNull
    @Positive
    private Integer price;

    @NotNull
    private EngineTypeEnum engine;

    @NotNull
    private TransmissionTypeEnum transmissionType;

    @NotNull
    @Min(1930)
    private Integer year;

    @Positive
    @NotNull
    private Integer mileage;

    @Size(min = 10, max = 512)
    private String description;

    @NotEmpty
    private String imageUrl;

}

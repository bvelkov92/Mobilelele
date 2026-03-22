package com.mobilele.model.DTOs.Model;

import com.mobilele.model.enums.TypeOfVehicleEnums;
import com.mobilele.utils.validators.AlreadyAddedModelValidation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddNewModelDto {

    @AlreadyAddedModelValidation
    @NotBlank
    private String name;

    @NotNull
    private TypeOfVehicleEnums categoryVehicle;

    @NotNull
    @Min(1800)
    private Integer startYear;

    @NotNull
    private Integer endYear;

}

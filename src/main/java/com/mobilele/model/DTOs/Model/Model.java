package com.mobilele.model.DTOs.Model;

import com.mobilele.model.enums.TypeOfVehicleEnums;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    @NotBlank
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private TypeOfVehicleEnums categoryVehicle;

    @NotBlank
    @Min(1800)
    private Integer startYear;

    @NotBlank
    private Integer endYear;

    @NotBlank
    private String image;

}

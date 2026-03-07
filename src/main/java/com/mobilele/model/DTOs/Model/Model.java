package com.mobilele.model.DTOs.Model;

import com.mobilele.model.enums.TypeOfVehicleEnums;
import jakarta.persistence.Column;
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

    private Long id;
    private String name;
    private TypeOfVehicleEnums categoryVehicle;
    private Integer startYear;
    private Integer endYear;
    private String image;

//    public Model(Long id, String name) {
//        this.id=id;
//        this.name = name;
//    }


}

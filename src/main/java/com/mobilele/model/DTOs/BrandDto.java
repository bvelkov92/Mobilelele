package com.mobilele.model.DTOs;

import com.mobilele.model.entity.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {

    private String brand;
    private List<ModelDTO>  vehicleModelsAboutThisBrand;

}

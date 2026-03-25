package com.mobilele.model.DTOs.Brand;

import com.mobilele.model.entity.Models;
import com.mobilele.utils.anotations.AlreadyAddedBrandsValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddNewBrand {

    @NotBlank
    @AlreadyAddedBrandsValidation
    private String brandName;

    private List<Models> models= new ArrayList<>();

}

package com.mobilele.model.DTOs.Brand;

import com.mobilele.model.DTOs.Model.Model;
import com.mobilele.utils.validators.AlreadyAddedBrandsValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AddNewBrand {

    @NotBlank
    @AlreadyAddedBrandsValidation
    private String brandName;


}

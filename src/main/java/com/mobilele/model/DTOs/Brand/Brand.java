package com.mobilele.model.DTOs.Brand;

import com.mobilele.model.DTOs.Model.GetModelDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    private Long id;

    @NotBlank
    private String name;

    @NotEmpty
    private List<GetModelDto>  models;

}

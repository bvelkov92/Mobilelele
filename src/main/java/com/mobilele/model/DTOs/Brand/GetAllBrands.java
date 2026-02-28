package com.mobilele.model.DTOs.Brand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetAllBrands {

    private String name;
    private String category;
    private Integer startYear;
    private Integer endYear;
    private String image;
}

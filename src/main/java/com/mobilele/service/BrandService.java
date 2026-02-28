package com.mobilele.service;

import com.mobilele.model.DTOs.Brand.Brand;
import com.mobilele.model.DTOs.Brand.GetAllBrands;
import com.mobilele.model.entity.Brands;

import java.util.List;

public interface BrandService {

    List<Brand> getAllModelsFromBrand();
    List<GetAllBrands> getAllBrands();

}

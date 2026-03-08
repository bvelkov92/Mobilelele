package com.mobilele.service;

import com.mobilele.model.DTOs.Brand.AddNewBrand;
import com.mobilele.model.DTOs.Brand.Brand;
import com.mobilele.model.entity.Brands;

import java.util.List;

public interface BrandService {

    List<Brand> getAllModelsFromBrand();
    List<Brands> getAllBrands();

    boolean addNewBrand(AddNewBrand brand);
}

package com.mobilele.service;

import com.mobilele.model.DTOs.Brand.AddNewBrand;
import com.mobilele.model.DTOs.Brand.Brand;
import com.mobilele.model.entity.Brands;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpqlQueryBuilder;

import java.util.List;

public interface BrandService {

    List<Brand> getAllBrandsAndTheirModels();
    List<Brand> getAllBrands();
    Brand getCurrentBrand(Long id);
    void addNewBrand(AddNewBrand brand);
    void deleteBrand(Long id);
}

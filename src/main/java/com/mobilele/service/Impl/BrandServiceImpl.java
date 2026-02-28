package com.mobilele.service.Impl;

import com.mobilele.model.DTOs.Brand.Brand;
import com.mobilele.model.DTOs.Brand.GetAllBrands;
import com.mobilele.model.DTOs.Model.Model;
import com.mobilele.repository.BrandRepository;
import com.mobilele.repository.ModelRepository;
import com.mobilele.service.BrandService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    public BrandServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Brand> getAllModelsFromBrand() {
        return this.brandRepository.findAll().stream()
                .map(currentBrand-> new Brand(
                        currentBrand.getName(),
                        modelRepository.findAllByBrand_Id(currentBrand.getId()).stream()
                                .map(model -> new Model(model.getId(), model.getName()))
                                .sorted(Comparator.comparing(Model::getName))
                                .collect(Collectors.toList())
                )).sorted(Comparator.comparing(Brand::getBrand))
                .collect(Collectors.toList());

    }

    @Override
    public List<GetAllBrands> getAllBrands() {

//
//        List<GetAllBrands> testBrand = this.brandRepository.findAll().stream()
//                .map(brand -> {
//                    GetAllBrands currentBrand = new GetAllBrands();
//                   // currentBrand.setCategory(brand.getCategory());
//                    currentBrand.setName(brand.getName());
//                    currentBrand.setImage(brand.getImage());
//                    currentBrand.setStartYear(brand.getStartYear());
//                    currentBrand.setEndYear(brand.getEndYear());
//                    return currentBrand;
//                }).toList();
//
//        System.out.printf("test");

        return null;
    }
}

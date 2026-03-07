package com.mobilele.service.Impl;

import com.mobilele.model.DTOs.Brand.Brand;
import com.mobilele.model.DTOs.Model.Model;
import com.mobilele.model.entity.Brands;
import com.mobilele.repository.BrandRepository;
import com.mobilele.repository.ModelRepository;
import com.mobilele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Brand> getAllModelsFromBrand() {
        return this.brandRepository.findAll().stream()
                .map(currentBrand-> new Brand(
                        currentBrand.getName(),
                        modelRepository.findAllByBrand_Id(currentBrand.getId()).stream()
                                .map(model -> new Model(model.getId(), model.getName(), model.getCategoryVehicle(), model.getStartYear(),
                                        model.getEndYear(),model.getImage()))
                                .sorted(Comparator.comparing(Model::getName))
                                .collect(Collectors.toList())
                )).sorted(Comparator.comparing(Brand::getName))
                .collect(Collectors.toList());

    }

    @Override
    public List<Brands> getAllBrands() {
        List<Brands> all = this.brandRepository.findAll();


        return all;
    }

}

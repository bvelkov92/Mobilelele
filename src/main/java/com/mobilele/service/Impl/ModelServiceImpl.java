package com.mobilele.service.Impl;

import com.mobilele.model.DTOs.Model.AddNewModelDto;
import com.mobilele.model.entity.Brands;
import com.mobilele.model.entity.Models;
import com.mobilele.repository.BrandRepository;
import com.mobilele.repository.ModelRepository;
import com.mobilele.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void addModelToBrand(Long brandId, AddNewModelDto newModel) {
        Models foundModel = this.modelRepository.findByName(newModel.getName()).orElse(null);
        boolean isModelFoundInSameBrand=false;
        if (foundModel!=null) {
            isModelFoundInSameBrand = this.brandRepository.findByIdAndModels_id(brandId, foundModel.getId()).isPresent();
        }

            if (isModelFoundInSameBrand) {
                foundModel.setName(newModel.getName().trim());
                foundModel.setStartYear(newModel.getStartYear());
                foundModel.setCategoryVehicle(newModel.getCategoryVehicle());
                foundModel.setBrand(this.brandRepository.getReferenceById(brandId));
                this.modelRepository.save(foundModel);
            } else {
                Models model = modelMapper.map(newModel, Models.class);
                model.setBrand(this.brandRepository.findById(brandId).orElseThrow());
                this.modelRepository.save(model);
            }
        }
    //}
}

package com.mobilele.service.Impl;

import com.mobilele.model.DTOs.Model.AddNewModelDto;
import com.mobilele.model.entity.Models;
import com.mobilele.repository.BrandRepository;
import com.mobilele.repository.ModelRepository;
import com.mobilele.service.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

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

        //TODO: This is for option "Edit model"
//        Models foundModel = this.modelRepository.findByName(newModel.getName()).orElse(null);
//        Brands foundBrand = this.brandRepository.findBrandAndModelsById(brandId).orElse(null);

//        boolean isModelFoundInSameBrand = false;
//        if (foundBrand!=null && foundModel!=null){
//          isModelFoundInSameBrand = foundBrand.getModels()
//                    .stream().anyMatch(model ->model.getName().trim().equals(foundModel.getName().trim()));
//        }

//            if (isModelFoundInSameBrand) {
//                foundModel.setName(newModel.getName().trim());
//                foundModel.setStartYear(newModel.getStartYear());
//                foundModel.setCategoryVehicle(newModel.getCategoryVehicle());
//                foundModel.setBrand(this.brandRepository.getReferenceById(brandId));
//                this.modelRepository.save(foundModel);
//            } else {

                Models model = modelMapper.map(newModel, Models.class);
                model.setBrand(this.brandRepository.findById(brandId).orElseThrow(()-> new RuntimeException("Brand not found!")));
                this.modelRepository.save(model);
        }

    @Override
    public Set<Models> getAllModelsFromBrand(Long brandId) {
        return this.modelRepository.findAllByBrand_Id(brandId);
    }

    @Transactional
    @Override
    public void deleteSelectedModel(Long bId,Long mId) {
        this.modelRepository.deleteByIdAndBrand_Id(mId,bId);
    }

}

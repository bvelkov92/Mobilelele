package com.mobilele.service;

import com.mobilele.model.DTOs.Model.AddNewModelDto;
import com.mobilele.model.entity.Models;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ModelService {
    void addModelToBrand(Long brandId, AddNewModelDto addModel);

    Set<Models> getAllModelsFromBrand(Long brandId);
}

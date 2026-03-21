package com.mobilele.service;

import com.mobilele.model.DTOs.Model.AddNewModelDto;
import com.mobilele.model.DTOs.Model.GetModelDto;
import org.springframework.stereotype.Service;

@Service
public interface ModelService {

    void addModelToBrand(Long brandId, AddNewModelDto addModel);
}

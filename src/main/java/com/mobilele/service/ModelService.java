package com.mobilele.service;

import com.mobilele.model.DTOs.Model.AddNewModelDto;
import org.springframework.stereotype.Service;

@Service
public interface ModelService {
    void addModelToBrand(Long brandId, AddNewModelDto addModel);
}

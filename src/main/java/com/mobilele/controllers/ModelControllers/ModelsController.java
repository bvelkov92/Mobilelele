package com.mobilele.controllers.ModelControllers;

import com.mobilele.model.enums.TypeOfVehicleEnums;
import com.mobilele.service.BrandService;
import com.mobilele.service.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ModelsController {

    private final ModelService modelService;
    private final BrandService brandService;

    public ModelsController(ModelService modelRepository, BrandService brandService) {
        this.modelService = modelRepository;
        this.brandService = brandService;
    }

    @ModelAttribute("typeOfVehicle")
    public TypeOfVehicleEnums[] typeOfVehicle() {
        return TypeOfVehicleEnums.values();
    }
    }

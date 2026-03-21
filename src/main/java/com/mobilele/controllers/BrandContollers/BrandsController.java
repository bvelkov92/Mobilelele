package com.mobilele.controllers.BrandContollers;

import com.mobilele.model.DTOs.Brand.AddNewBrand;
import com.mobilele.model.DTOs.Brand.Brand;
import com.mobilele.service.BrandService;
import com.mobilele.service.ModelService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandsController {

    private final BrandService brandService;
    private final ModelService modelService;

    public BrandsController(BrandService brandService, ModelService modelService) {
        this.brandService = brandService;
        this.modelService = modelService;
    }


    @GetMapping("/all")
    public String getAllBrands(Model model){
        model.addAttribute("brands", brandService.getAllBrandsAndTheirModels());
        return "brands";
    }

}

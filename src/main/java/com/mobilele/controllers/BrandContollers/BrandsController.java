package com.mobilele.controllers.BrandContollers;

import com.mobilele.service.BrandService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/brands")
public class BrandsController {

    private final BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;

    }


    @GetMapping("/all")
    public String getAllBrands(Model model){
        model.addAttribute("brands", brandService.getAllBrandsAndTheirModels());
        return "brands";
    }

}

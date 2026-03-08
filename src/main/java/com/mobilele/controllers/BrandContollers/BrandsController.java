package com.mobilele.controllers.BrandContollers;

import com.mobilele.model.DTOs.Brand.AddNewBrand;
import com.mobilele.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/brands")
public class BrandsController {

    private final BrandService brandService;

    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }


    @GetMapping("/all")
    public String getAllBrands(Model model){
        model.addAttribute("brands", brandService.getAllModelsFromBrand());
        return "brands";
    }

    @GetMapping("/add")
    public String addNewBrand(Model model){
        if (!model.containsAttribute("addNewBrand")) {
            model.addAttribute("addNewBrand",new AddNewBrand());
        }

        return "add-new-brand";
    }

    @PostMapping()
    public String addNewBrand(@Valid AddNewBrand addNewBrand,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addNewBrand", addNewBrand);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addNewBrand", bindingResult);
        }

        this.brandService.addNewBrand(addNewBrand);
        return "redirect/:brands/all";

    }
}

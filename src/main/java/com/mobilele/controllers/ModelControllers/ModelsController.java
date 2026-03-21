package com.mobilele.controllers.ModelControllers;

import com.mobilele.model.DTOs.Brand.Brand;
import com.mobilele.model.DTOs.Model.AddNewModelDto;
import com.mobilele.model.DTOs.Model.GetModelDto;
import com.mobilele.model.enums.TypeOfVehicleEnums;
import com.mobilele.service.BrandService;
import com.mobilele.service.ModelService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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



    @GetMapping("/brands/{id}/models")
    public String addNewModel(@PathVariable Long id, Model model){
        Brand currentBrand = this.brandService.getCurrentBrand(id);
            model.addAttribute("getOrAddModel", currentBrand);
            model.addAttribute("typeOfVehicle", TypeOfVehicleEnums.values());
            model.addAttribute("addNewModelDto", new AddNewModelDto());
            return "models-in-selected-brand";
    }

    @PostMapping("/brands/{id}/models")
    public String addNewModel(@PathVariable Long id, @Valid AddNewModelDto addNewModelDto,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("AddNewModelDto", addNewModelDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addNewModelDto", bindingResult);
          return   "redirect:/brands/" + id + "/models";
        }

        this.modelService.addModelToBrand(id, addNewModelDto);
        return "redirect:/";
    }
    }

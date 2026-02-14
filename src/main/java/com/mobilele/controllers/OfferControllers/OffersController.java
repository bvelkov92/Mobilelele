package com.mobilele.controllers.OfferControllers;

import com.mobilele.model.DTOs.AddOfferDto;
import com.mobilele.model.enums.EngineTypeEnum;
import com.mobilele.model.enums.TypeOfVehicleEnums;
import com.mobilele.model.enums.TransmissionTypeEnum;
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
@RequestMapping("/offers")
public class OffersController {

    private final BrandService brandService;

    public OffersController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public String getAllOffers(){

        return "offers";
    }

    @GetMapping("/add")
    public String getAddOfferForm(Model model){

        if (!model.containsAttribute("addOfferDto")){
            model.addAttribute("addOfferDto", new AddOfferDto());
        }
        model.addAttribute("brand", brandService.getAllModelsFromBrand());
        model.addAttribute("engines", EngineTypeEnum.values());
        model.addAttribute("transmissionTypes", TransmissionTypeEnum.values());
        model.addAttribute("typeOfVehicle", TypeOfVehicleEnums.values());

         return "offer-add";
    }

    @PostMapping("/add")
    public String postAddOfferForCar(@Valid AddOfferDto addOfferDto,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addOfferDto", addOfferDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addOfferDto", bindingResult);
            return "redirect:/offers/add";
        }

        return "redirect:/offers/all";
    }

}

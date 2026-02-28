package com.mobilele.controllers.OfferControllers;

import com.mobilele.model.DTOs.Offer.SaveOffer;
import com.mobilele.model.DTOs.Offer.CurrentOfferDetails;
import com.mobilele.model.DTOs.Offer.UpdateOffer;
import com.mobilele.model.enums.EngineTypeEnum;
import com.mobilele.model.enums.TypeOfVehicleEnums;
import com.mobilele.model.enums.TransmissionTypeEnum;
import com.mobilele.service.BrandService;
import com.mobilele.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/offers")
public class OffersController {

    private final BrandService brandService;
    private final OfferService offerService;

    public OffersController(BrandService brandService, OfferService offerService) {
        this.brandService = brandService;
        this.offerService = offerService;
    }

    @ModelAttribute("brands")
    public Object brands() {
        return brandService.getAllModelsFromBrand();
    }

    @ModelAttribute("engines")
    public EngineTypeEnum[] engines() {
        return EngineTypeEnum.values();
    }

    @ModelAttribute("transmissionTypes")
    public TransmissionTypeEnum[] transmissionTypes() {
        return TransmissionTypeEnum.values();
    }

    @ModelAttribute("typeOfVehicle")
    public TypeOfVehicleEnums[] typeOfVehicle() {
        return TypeOfVehicleEnums.values();
    }

    @GetMapping("/all")
    public String getAllOffers(Model model){
        model.addAttribute("offers", offerService.getAllOffers());
        return "offers";
    }

    @GetMapping("/add")
    public String AddOffer(Model model){

        if (!model.containsAttribute("addOfferDto")){
            model.addAttribute("addOfferDto", new SaveOffer());
        }

         return "offer-add";
    }

    @PostMapping("/add")
    public String AddOffer(@Valid SaveOffer saveOffer,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("saveOffer", saveOffer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.saveOffer", bindingResult);
            return "redirect:/offers/add";
        }
            this.offerService.saveOffer(saveOffer);

        return "redirect:/offers/all";
    }

    @GetMapping("/{id}/details")
    public String getDetails(@PathVariable Long id, Model model){
        model.addAttribute("offer", offerService.getDetails(id));
        return "details";
    }

    @GetMapping("/{id}/update")
    public String updateOffer (@PathVariable Long id, Model model){

        UpdateOffer updateOffer = offerService.getUpdateDtoDetails(id);
        model.addAttribute("updateOffer", updateOffer);
          return "update";
    }

    @PostMapping("/{id}/update")
    public String updateOffer(@PathVariable Long id, @Valid UpdateOffer updateOffer,
                                      BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("updateOffer", updateOffer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateOffer", bindingResult);

            return "redirect:/offers/" + id + "/update";
        }

        this.offerService.updateOffer(updateOffer, id);

        System.out.printf("TODO: ");

        return "redirect:/offers/" + id + "/details";
    }


}

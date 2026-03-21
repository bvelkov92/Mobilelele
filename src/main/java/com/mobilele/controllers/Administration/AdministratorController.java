package com.mobilele.controllers.Administration;

import com.mobilele.model.DTOs.Brand.AddNewBrand;
import com.mobilele.service.BrandService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/administration")
public class AdministratorController {

    private final BrandService brandService;

    public AdministratorController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/brandsAndModels")
    public String addNewBrand(Model model){
        if (!model.containsAttribute("addNewBrand")) {
            model.addAttribute("addNewBrand",new AddNewBrand());
        }
        model.addAttribute("getAllBrands", this.brandService.getAllBrands());
        return "add-new-brand";
    }

    @PostMapping("/brandsAndModels")
    public String addNewBrand(@Valid AddNewBrand addNewBrand,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addNewBrand", addNewBrand);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addNewBrand", bindingResult);
            return "redirect:/add";
        }
        this.brandService.addNewBrand(addNewBrand);
        return "redirect:/all";
    }

    @PostMapping("/{id}/delete")
    public String deleteBrand(@PathVariable Long id){
        this.brandService.deleteBrand(id);
        return "redirect:/administration/brandsAndModels";
    }

}

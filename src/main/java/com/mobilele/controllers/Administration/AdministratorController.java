package com.mobilele.controllers.Administration;

import com.mobilele.model.DTOs.Brand.AddNewBrand;
import com.mobilele.model.DTOs.Brand.Brand;
import com.mobilele.model.DTOs.Model.AddNewModelDto;
import com.mobilele.model.enums.TypeOfVehicleEnums;
import com.mobilele.service.BrandService;
import com.mobilele.service.ModelService;
import com.mobilele.service.UserService;
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
    private final ModelService modelService;
    private final UserService userService;

    public AdministratorController(BrandService brandService, ModelService modelService, UserService userService) {
        this.brandService = brandService;
        this.modelService = modelService;
        this.userService = userService;
    }


    //========================== B R A N D S   A N D   M O D E L S =========================


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

    @GetMapping("/brands/{id}/models")
    public String addNewModel(@PathVariable Long id, Model model){
        Brand currentBrand = this.brandService.getCurrentBrand(id);
        model.addAttribute("getOrAddModel", currentBrand);
        model.addAttribute("typeOfVehicle", TypeOfVehicleEnums.values());

        if (!model.containsAttribute("addNewModelDto")) {
            model.addAttribute("addNewModelDto", new AddNewModelDto());
        }
        return "models-in-selected-brand";
    }

    @PostMapping("/brands/{id}/models")
    public String addNewModel(@PathVariable Long id, @Valid AddNewModelDto addNewModelDto,
                              BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("addNewModelDto", addNewModelDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addNewModelDto", bindingResult);
            return   "redirect:/brands/" + id + "/models";
        }


        this.modelService.addModelToBrand(id, addNewModelDto);
        return "redirect:/";
    }


    /// ============================ U S E R S    A D M I N I S T R A T I O N =======================================

    @GetMapping("/users")
    public String getUserAllUsers(Model model){
        if (!model.containsAttribute("getAllUsers")){
            model.addAttribute("getAllUsers" , userService.getAllUsers());
            model.addAttribute("loggedUserId", userService.getUserId());
        }
        return "users-administration";
    }


    @PostMapping("/users/{id}/delete")
    public String postDeleteSelectedUser(@PathVariable Long id){
        this.userService.deleteUserWithId(id);

        return "redirect:/administration/users";
    }


}

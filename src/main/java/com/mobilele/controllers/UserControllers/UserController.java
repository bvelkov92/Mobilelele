package com.mobilele.controllers.UserControllers;

import com.mobilele.model.DTOs.User.ChangePassword;
import com.mobilele.model.DTOs.User.EditProfile;
import com.mobilele.model.DTOs.User.UserLogin;
import com.mobilele.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //======================= L O G I N =============================ø
    @GetMapping("/login")
    public String getLogin(Model model) {
        if (!model.containsAttribute("userLoginDto")) {
            model.addAttribute("userLoginDto", new UserLogin());
        }
        return "auth-login";
    }

    @GetMapping("/profile")
    public String getMyProfile(Model model){
        model.addAttribute("profile", this.userService.getLoggedUser());
        return "person-profile";
    }


    @GetMapping("/profile/editProfile")
    public String getUpdateProfile(Model model){
        if (!model.containsAttribute("editProfile")){
            model.addAttribute("editProfile", this.userService.getEditProfileDto());
        }

        return  "edit-profile";
    }


    @PostMapping("/profile/editProfile")
    @Transactional
    public String postUpdateProfile(@Valid EditProfile editProfileDto,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes,
                                    @RequestParam ("profileImg") MultipartFile file){

        //EditProfile user = this.userService.getEditProfileDto();

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("editProfile",editProfileDto );
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.editProfile", bindingResult);
            return "redirect:/users/profile/editProfile";
        }
            redirectAttributes.addFlashAttribute("profileImg", true);

        this.userService.updateProfile(editProfileDto, file);

        return "redirect:/users/profile";
    }

    @GetMapping("/profile/changePassword")
    public String getChangePassword(Model model){
        if (!model.containsAttribute("changePassword")){
            model.addAttribute("changePassword", new ChangePassword());
        }
        return "change-password";
    }

    @Transactional
    @PostMapping("/profile/changePassword")
    public String postUpdateProfile(@Valid ChangePassword changePassword,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes){



        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("changePassword", changePassword);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changePassword", bindingResult);

        return "redirect:/users/profile/changePassword";
        }

        try {
            this.userService.changePassword(changePassword);
            return "redirect:/users/profile";
        }catch (RuntimeException message){
            redirectAttributes.addFlashAttribute("changePassword", changePassword);
            redirectAttributes.addFlashAttribute("invalidPassword", true);
            return "redirect:/users/profile/changePassword";
        }
    }
}


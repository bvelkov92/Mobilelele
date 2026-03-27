package com.mobilele.controllers.UserControllers;

import com.mobilele.model.DTOs.User.ChangePassword;
import com.mobilele.model.DTOs.User.EditProfile;
import com.mobilele.model.DTOs.User.UserLogin;
import com.mobilele.model.entity.Users;
import com.mobilele.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        Users users = this.userService.getAuthenticatedUser();
        model.addAttribute("profile", users);
        return "person-profile";
    }


    @GetMapping("/profile/editProfile")
    public String getUpdateProfile(Model model){
        if (!model.containsAttribute("editProfile")){
            model.addAttribute("editProfile", new EditProfile());
        }

        //TODO:
        return  null;
    }


    @PostMapping("/profile/editProfile")
    public String postUpdateProfile(){

        //TODO:
        return null;
    }

    @GetMapping("/profile/changePassword")
    public String getChangePassword(Model model){
        if (!model.containsAttribute("changePassword")){
            model.addAttribute("changePassword", new ChangePassword());
        }
        return "change-password";
    }

    @PostMapping("/profile/changePassword")
    public String postUpdateProfile(@Valid ChangePassword changePassword,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("changePassword", changePassword);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.changePassword", bindingResult);

        return "redirect:/users/profile/changePassword";
        }
        Users loggedUser = this.userService.getAuthenticatedUser();
        this.userService.changePassword(changePassword, loggedUser);

        return "redirect:/users/profile";
    }
}


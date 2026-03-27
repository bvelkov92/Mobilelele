package com.mobilele.controllers.UserControllers;

import com.mobilele.model.DTOs.User.EditProfile;
import com.mobilele.model.DTOs.User.UserLogin;
import com.mobilele.model.entity.Users;
import com.mobilele.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        Users users = this.userService.getUserById();
        model.addAttribute("profile", users);
        return "person-profile";
    }


    @GetMapping("/users/profile/editProfile")
    public String getUpdateProfile(Model model){
        if (!model.containsAttribute("editProfile")){
            model.addAttribute("editProfile", new EditProfile());
        }
        return  null;
    }


    @PostMapping("/users/profile/editProfile")
    public String postUpdateProfile(){
        return null;
    }

}


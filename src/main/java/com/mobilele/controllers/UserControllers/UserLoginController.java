package com.mobilele.controllers.UserControllers;

import com.mobilele.model.DTOs.UserLoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserLoginController {

    //======================= L O G I N =============================ø
    @GetMapping("/login")
    public String getLogin(Model model) {

        if (!model.containsAttribute("userLoginDto")) {
            model.addAttribute("userLoginDto", new UserLoginDTO());
        }
        return "auth-login";
    }
}


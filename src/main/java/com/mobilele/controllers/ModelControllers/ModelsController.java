package com.mobilele.controllers.ModelControllers;
import com.mobilele.model.enums.TypeOfVehicleEnums;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ModelsController {

    @ModelAttribute("typeOfVehicle")
    public TypeOfVehicleEnums[] typeOfVehicle() {
        return TypeOfVehicleEnums.values();
    }

    }

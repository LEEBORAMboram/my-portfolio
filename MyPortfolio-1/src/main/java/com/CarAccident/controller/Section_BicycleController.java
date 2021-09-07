package com.CarAccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Section_BicycleController {
    @GetMapping("/section_bicycle") 
    public String getBic() {
    
        return "/section_bicycle/section_bicycle";
    }
}

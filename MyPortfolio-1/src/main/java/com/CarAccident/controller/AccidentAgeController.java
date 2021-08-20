package com.CarAccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccidentAgeController {
    @GetMapping("/age") 
    public String getAge() {
        return "/age/age";
    }
}

package com.CarAccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Section_RegionalController {
    @GetMapping("/section_regional") 
    public String getRei() {
    
        return "/section_regional/section_regional";
    }
}

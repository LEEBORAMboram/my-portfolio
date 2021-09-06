package com.CarAccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Section_TwowheeledController {
    @GetMapping("/section_twowheeled") 
    public String getSectionTwowheeled() {
    
        return "/section_twowheeled/section_twowheeled";
    }
}

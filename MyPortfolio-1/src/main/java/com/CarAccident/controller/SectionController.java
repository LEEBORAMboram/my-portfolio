package com.CarAccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SectionController {
    @GetMapping("/section") 
    public String getReigion() {
    
        return "/section/section";
    }
}

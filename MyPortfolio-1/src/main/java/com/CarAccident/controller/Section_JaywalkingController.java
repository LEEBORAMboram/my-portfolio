package com.CarAccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Section_JaywalkingController {
    @GetMapping("/section_jaywalking") 
    public String getJay() {
    
        return "/section_jaywalking/section_jaywalking";
    }
}

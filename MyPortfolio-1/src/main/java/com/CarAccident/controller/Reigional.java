package com.CarAccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Reigional {
    @GetMapping("/regional") 
    public String getReigion() {
    
        return "/regional/regional";
    }
}

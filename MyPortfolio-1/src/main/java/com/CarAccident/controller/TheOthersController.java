package com.CarAccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TheOthersController {
    @GetMapping("/theothers")
    public String getOther() {
        
        return "/theothers/theothers";
    }
}

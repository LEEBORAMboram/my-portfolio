package com.CarAccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Section_SeniorController {
    @GetMapping("/section_senior")
    public String getsenior() {

        return "/section_senior/section_senior";
    }
}

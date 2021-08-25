package com.CarAccident.controller;

import com.CarAccident.service.CarRegionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    CarRegionalService service2;

    @GetMapping("/")
    public String getMain() {

        return "/index";
    }
}

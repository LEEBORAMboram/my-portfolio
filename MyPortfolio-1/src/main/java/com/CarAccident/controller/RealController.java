package com.CarAccident.controller;
import com.CarAccident.mapper.RealInfoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RealController {
    @Autowired
    RealInfoMapper mapper;

    @GetMapping("/realinfo")
    public String getReal() { 

        return "/realinfo/realinfo";
    }
}

package com.CarAccident.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class real {
 
    @GetMapping("real")
    public String getRegionalReal() {
        
        return "/real/real";
    }
}

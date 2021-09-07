package com.CarAccident.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OutlineController {
    @GetMapping("/outline")
        public String getOutline() {
            
            return "/outline/outline";
        }
    }

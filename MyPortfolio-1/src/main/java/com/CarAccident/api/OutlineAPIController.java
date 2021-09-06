package com.CarAccident.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OutlineAPIController {
    @GetMapping("/api/outline")
    public Map<String, Object> getOut() {
    Map<String, Object> resulMap = new LinkedHashMap<String, Object>();


    return resulMap;
    }
}

package com.CarAccident.api;

import java.util.LinkedHashMap;
import java.util.Map;

import com.CarAccident.service.SectionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Section_MainAPIController {
@Autowired
SectionService service4;

@GetMapping("/api/section/main")
public Map<String, Object> getSectionMain() throws Exception {
    Map<String, Object> resultMap =  new LinkedHashMap<String, Object>();

    return resultMap;
    }
}

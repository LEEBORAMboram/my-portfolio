package com.CarAccident.api;

import com.CarAccident.service.CarAccidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Regional {
    @Autowired
    CarAccidentService service;

    @GetMapping("/api/regional")
    public String getRegion() {
        
        return "/region/region";
    }
    //public Map<String, Object> getRegion(@RequestParam String region) {
    //     Map<String, Object> resulMap = new LinkedHashMap<String, Object>();
    
    //     List<RegionalInfoVO> list = service.selectSidoInfo(region);
    //     resulMap.put("list", list);
    //     return resulMap;
    // }
}

package com.CarAccident.api;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.CarAccident.service.TheOthersService;
import com.CarAccident.vo.RegionalInfoVO;
import com.CarAccident.vo.TemperatureVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TheOthersAPIController {
    @Autowired
    TheOthersService service3;

    @GetMapping("/api/theothers/rainfall")
    public Map<String, Object> getTheOthers(
        @RequestParam String tm_month, @RequestParam double rainfall,
        @RequestParam String region_acc_cnt, @RequestParam String region_month
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();


        List<TemperatureVO> rainfallList = service3.temperatureYearRainfall(tm_month, rainfall);
        // model.addAttribute("rainfallList", rainfallList);
        resultMap.put("rainfallList", rainfallList);

        List<RegionalInfoVO> accidentCntList = service3.selectaccidentCnt(region_acc_cnt, region_month);
        // model.addAttribute("accidentCntList", accidentCntList);
        resultMap.put("accidentCntList", accidentCntList);

        resultMap.put("status", true);
        return resultMap;
    }

    @GetMapping("/api/theothers/wind")
    public Map<String, Object> getWindInfo(
        @RequestParam String tm_month, @RequestParam Double wind,
        @RequestParam String region_acc_cnt, @RequestParam String region_month
    ){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
            
            List<TemperatureVO> windList = service3.temperatureYearWind(tm_month, wind);
            resultMap.put("windList", windList);

            List<RegionalInfoVO> accidentCntList = service3.selectaccidentCnt(region_acc_cnt, region_month);
            // model.addAttribute("accidentCntList", accidentCntList);
            resultMap.put("accidentCntList", accidentCntList);

            resultMap.put("status", true);
            return resultMap;
    }
    @GetMapping("/api/theothers/temp")
    public Map<String, Object> getTempInfo(
        @RequestParam String tm_month, @RequestParam Double temp,
        @RequestParam String region_acc_cnt, @RequestParam String region_month
    ){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
            
            List<TemperatureVO> temperatureList = service3.temperatureYear(tm_month, temp);
            resultMap.put("temperatureList", temperatureList);

            List<RegionalInfoVO> accidentCntList = service3.selectaccidentCnt(region_acc_cnt, region_month);
            // model.addAttribute("accidentCntList", accidentCntList);
            resultMap.put("accidentCntList", accidentCntList);

            resultMap.put("status", true);
            return resultMap;
    }
}

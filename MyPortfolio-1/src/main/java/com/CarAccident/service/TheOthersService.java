package com.CarAccident.service;

import java.util.List;

import com.CarAccident.mapper.TheOthersMapper;
import com.CarAccident.vo.RegionalInfoVO;
import com.CarAccident.vo.TemperatureVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheOthersService {
    @Autowired
    TheOthersMapper mapper;

    public  List<TemperatureVO> temperatureYearRainfall(String tm_month, Double rainfall) {
        return mapper.temperatureYearRainfall(tm_month, rainfall);
    }
    public List<TemperatureVO>temperatureYearWind(String tm_month, Double wind) {
        return mapper.temperatureYearWind(tm_month, wind);
    }
    public List<TemperatureVO>temperatureYear(String tm_month, Double temp) {
        return mapper.temperatureYear(tm_month, temp);
    }
    public List<RegionalInfoVO>selectaccidentCnt(String region_acc_cnt, String region_month) {
        return mapper.selectaccidentCnt(region_acc_cnt, region_month);
    }
    
}

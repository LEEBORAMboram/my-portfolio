package com.CarAccident.mapper;


import java.util.List;

import com.CarAccident.vo.RegionalInfoVO;
import com.CarAccident.vo.TemperatureVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TheOthersMapper {
    public List<TemperatureVO>temperatureYearWind(String tm_month, Double wind);
    public List<TemperatureVO>temperatureYear(String tm_month, Double temp);
    public List<TemperatureVO> temperatureYearRainfall(String tm_month, Double rainfall);
    public List<RegionalInfoVO>selectaccidentCnt(String region_acc_cnt, String region_month);
}

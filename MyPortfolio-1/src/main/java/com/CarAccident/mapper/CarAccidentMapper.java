package com.CarAccident.mapper;


import com.CarAccident.vo.CarAccidentVO;
import com.CarAccident.vo.RegionalInfoVO;
import com.CarAccident.vo.TemperatureVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarAccidentMapper {
    public void insertCarAccidentInfo(CarAccidentVO vo);
    public void insertRegionalInfo(RegionalInfoVO vo);
    public void inserttemperatureInfo(TemperatureVO vo);   
}

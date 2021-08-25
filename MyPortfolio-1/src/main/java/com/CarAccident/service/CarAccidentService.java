package com.CarAccident.service;

import com.CarAccident.mapper.CarAccidentMapper;
import com.CarAccident.mapper.CarRegionalMapper;
import com.CarAccident.vo.CarAccidentVO;
import com.CarAccident.vo.RegionalInfoVO;
import com.CarAccident.vo.TemperatureVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarAccidentService {
    @Autowired
    CarAccidentMapper mapper;
    @Autowired
    CarRegionalMapper mapper2;

    public void insertCarAccidentInfo(CarAccidentVO vo) {
        mapper.insertCarAccidentInfo(vo);
    }
    public void insertRegionalInfo(RegionalInfoVO vo) {
        mapper.insertRegionalInfo(vo);
    }
   
    public void inserttemperatureInfo(TemperatureVO vo) {
        mapper.inserttemperatureInfo(vo);
    }
}

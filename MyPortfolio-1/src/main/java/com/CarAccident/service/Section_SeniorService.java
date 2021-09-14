package com.CarAccident.service;

import java.util.List;

import com.CarAccident.mapper.Section_SeniorMapper;
import com.CarAccident.vo.Section_SeniorVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Section_SeniorService {
    @Autowired
    Section_SeniorMapper mapper;

    public void insertSenior(Section_SeniorVO vo) {
        mapper.insertSenior(vo);
    }
    public List<Section_SeniorVO> selectSeniorRegionBunch(String region) {
        return mapper.selectSeniorRegionBunch(region);
    }
    public List<String> selectSeniorRegionName() {
        return mapper.selectSeniorRegionName();
    }
    public List<String> getSeniorSigunguNames(String spot) {
        return mapper.getSeniorSigunguNames(spot);
    }
    public List<Section_SeniorVO> selectAddressSenior(String selectbox) {
        return mapper.selectAddressSenior(selectbox);
    }
}

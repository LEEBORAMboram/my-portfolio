package com.CarAccident.service;

import java.util.List;

import com.CarAccident.mapper.Section_RegionalMapper;
import com.CarAccident.vo.Section_RegionalVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class Section_RegionalService {
    @Autowired
    Section_RegionalMapper mapper;

    public void insertSectionRegional(Section_RegionalVO vo) {
        mapper.insertSectionRegional(vo);
    }
    public List<Section_RegionalVO> selectSectionRegional(String region){
        return mapper.selectSectionRegional(region);
    }
    public List<String> selectRegionalName(){
        return mapper.selectRegionalName();
    }
    public List<String> selectRegionalSigunguName(String spot){
        return mapper.selectRegionalSigunguName(spot);
    }
    public List<Section_RegionalVO> selectRegionalAddress(String selectbox){
        return mapper.selectRegionalAddress(selectbox);
    }
}

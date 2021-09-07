package com.CarAccident.service;

import java.util.List;

import com.CarAccident.mapper.SectionMapper;
import com.CarAccident.vo.Section_bicycleVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SectionService {
    @Autowired
    SectionMapper mapper;

    public void insertBicycleInfo(Section_bicycleVO vo) {
        mapper.insertBicycleInfo(vo);
    }
    public List<Section_bicycleVO>selectRegionBunch(String region) {
        return mapper.selectRegionBunch(region);
    }
    public List<String> getRegionNames() {
        return mapper.getRegionNames();
    }
    public List<String> selectSigunguName(String spot) {
        return mapper.selectSigunguName(spot);
    }
    public List<Section_bicycleVO> selectBox(String selectbox) {
        return mapper.selectBox(selectbox);
    }
}

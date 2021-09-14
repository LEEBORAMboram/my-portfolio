package com.CarAccident.service;

import java.util.List;

import com.CarAccident.mapper.Section_JaywalkingMapper;
import com.CarAccident.vo.Section_JaywalkingVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Section_JaywalkingService {
    @Autowired
    Section_JaywalkingMapper mapper;
    
    public void insertSectionJaywalking(Section_JaywalkingVO vo) {
        mapper.insertSectionJaywalking(vo);
    }
    public List<String> selectRegionName() {
        return mapper.selectRegionName();
    }
    public List<String> getSigunguNames(String spot) {
        return mapper.getSigunguNames(spot);
    }
    public List<Section_JaywalkingVO> selectAddressJaywalking(String selectbox) {
        return mapper.selectAddressJaywalking(selectbox);
    }
    public List<Section_JaywalkingVO> selectJaywalkingRegionBunch(String region) {
        return mapper.selectJaywalkingRegionBunch(region);
    }
}

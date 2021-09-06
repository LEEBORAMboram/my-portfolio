package com.CarAccident.service;

import java.util.List;

import com.CarAccident.mapper.Section_TwowheeledMapper;
import com.CarAccident.vo.Section_TwowheeledVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Section_TwowheeledService {
    @Autowired
    Section_TwowheeledMapper mapper;

    public void insertSectionTwowheeled(Section_TwowheeledVO vo) {
        mapper.insertSectionTwowheeled(vo);
    }
    public List<Section_TwowheeledVO> selectSectionTwowheeled(String region) {
        return mapper.selectSectionTwowheeled(region);
    }
    public List<String> selectTwowheeledName() {
        return mapper.selectTwowheeledName();
    }
    public List<String> selectTwowheeledSigunguName(String spot) {
        return mapper.selectTwowheeledSigunguName(spot);
    }
    public List<Section_TwowheeledVO>  selectTwowheeledAddress(String selectbox) {
        return mapper.selectTwowheeledAddress(selectbox);
    }
}

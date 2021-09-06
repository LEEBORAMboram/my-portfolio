package com.CarAccident.mapper;

import java.util.List;

import com.CarAccident.vo.Section_RegionalVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Section_RegionalMapper {
    public void insertSectionRegional(Section_RegionalVO vo);
    public List<Section_RegionalVO> selectSectionRegional(String region);
    public List<String> selectRegionalName();
    public List<String> selectRegionalSigunguName(String spot);
    public List<Section_RegionalVO> selectRegionalAddress(String selectbox);
}

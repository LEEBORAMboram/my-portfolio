package com.CarAccident.mapper;

import java.util.List;

import com.CarAccident.vo.Section_SeniorVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Section_SeniorMapper {
    public void insertSenior(Section_SeniorVO vo);
    public List<Section_SeniorVO> selectSeniorRegionBunch(String region);    
    public List<String> selectSeniorRegionName(); 
    public List<String> getSeniorSigunguNames(String spot);   
    public List<Section_SeniorVO> selectAddressSenior(String selectbox);
}
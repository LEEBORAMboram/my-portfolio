package com.CarAccident.mapper;

import java.util.List;

import com.CarAccident.vo.Section_JaywalkingVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Section_JaywalkingMapper {
    public void insertSectionJaywalking(Section_JaywalkingVO vo);
    public List<String> selectRegionName();
    public List<String> getSigunguNames(String spot);
    public List<Section_JaywalkingVO> selectAddressJaywalking(String selectbox);
    public List<Section_JaywalkingVO> selectJaywalkingRegionBunch(String region);
}

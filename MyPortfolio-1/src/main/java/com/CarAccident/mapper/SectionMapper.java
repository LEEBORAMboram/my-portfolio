package com.CarAccident.mapper;

import java.util.List;

import com.CarAccident.vo.Section_bicycleVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SectionMapper {
    public void insertBicycleInfo (Section_bicycleVO vo);
    public List<Section_bicycleVO>selectRegionBunch(String region);
    public List<String> getRegionNames();
    public List<String> selectSigunguName(String spot);
    public List<Section_bicycleVO> selectBox(String selectbox);
    
}

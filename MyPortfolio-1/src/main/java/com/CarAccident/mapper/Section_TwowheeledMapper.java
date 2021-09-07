package com.CarAccident.mapper;

import java.util.List;

import com.CarAccident.vo.Section_TwowheeledVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface Section_TwowheeledMapper {
    public void insertSectionTwowheeled(Section_TwowheeledVO vo);
    public List<Section_TwowheeledVO> selectSectionTwowheeled(String region);
    public List<String> selectTwowheeledName();
    public List<String> selectTwowheeledSigunguName(String spot);
    public List<Section_TwowheeledVO>  selectTwowheeledAddress(String selectbox);
}

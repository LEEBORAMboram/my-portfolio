package com.CarAccident.mapper;

import java.util.List;

import com.CarAccident.vo.CarSectionVO;
import com.CarAccident.vo.RegionalInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarRegionalMapper {
    public RegionalInfoVO selectCarAccidentInfo(String age);
    public void insertCarSection(CarSectionVO vo);
    public List<RegionalInfoVO> selectSidoInfo(String region);
}

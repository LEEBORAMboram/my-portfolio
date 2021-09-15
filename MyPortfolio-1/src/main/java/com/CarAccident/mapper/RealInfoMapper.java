package com.CarAccident.mapper;

import java.util.List;

import com.CarAccident.vo.RealInfoVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RealInfoMapper {
    public void insertRealInfo(RealInfoVO vo);
    public List<RealInfoVO> selectRealtime(String spot);
    public List<String>selectRegionNameCategory();
    public List<RealInfoVO> searchName(String search);
    public List<RealInfoVO> selectrealSigungu(String sigungu);
}

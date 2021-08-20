package com.CarAccident.mapper;

import com.CarAccident.vo.CarAccidentVO;
import com.CarAccident.vo.RegionalInfoVO;
import com.CarAccident.vo.TrafficVolumeVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarAccidentMapper {
    public void selectCarAccidentInfo(CarAccidentVO vo);
    public void selectRegionalInfo(RegionalInfoVO vo);
    public void selectTrafficVolumeInfo(TrafficVolumeVO vo);
}

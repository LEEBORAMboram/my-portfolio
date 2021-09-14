package com.CarAccident.mapper;

import java.util.List;

import com.CarAccident.vo.CarSectionVO;
import com.CarAccident.vo.RealTrafficVO;
import com.CarAccident.vo.RegionalInfoVO;
import com.CarAccident.vo.RoadriskindexVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarRegionalMapper {
    public RegionalInfoVO selectCarAccidentInfo(String age);
    public void insertCarSection(CarSectionVO vo);
    public List<RegionalInfoVO> selectSidoInfo(String region);
    public void insertRealTrafficInfo(RealTrafficVO vo);
    public List<RealTrafficVO> selectRealTrafficInfo(String date, String amount, String time);
    public List<RealTrafficVO> selectOption(String name);
    public List<CarSectionVO> selectRealConstrutionInfo(String date);
    
    public void insertRoadRiskIndex(RoadriskindexVO vo);
    public List<RoadriskindexVO> selecthighwayName(String name);
    public List<String> highwayName();
    public List<String> highwaySection(String highway);
    public List<String> selectLatitudeFrist(String highway, Integer grade, String highway_section);            
    public List<String> selectLongitudeFirst(String highway, Integer grade, String highway_section);        
    public List<String> selectLatitudeEnd(String highway, Integer grade, String highway_section);
    // public List<String> selectLongitudeEnd(String highway, Integer grade, String highway_section);
    public List<RoadriskindexVO> selectLongitudeEnd(String highway, Integer grade, String highway_section);
}

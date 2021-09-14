package com.CarAccident.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.CarAccident.mapper.CarRegionalMapper;
import com.CarAccident.vo.CarSectionVO;
import com.CarAccident.vo.RealTrafficVO;
import com.CarAccident.vo.RegionalInfoVO;
import com.CarAccident.vo.RoadriskindexVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarRegionalService {
    @Autowired
    CarRegionalMapper mapper;
    
    public void insertCarSection(CarSectionVO vo) {
        mapper.insertCarSection(vo);
    }
    public List<CarSectionVO> selectRealTodayConstrunction() {

        Calendar now = Calendar.getInstance();
        Calendar standard = Calendar.getInstance();
        standard.set(Calendar.HOUR_OF_DAY, 10);
        standard.set(Calendar.MINUTE, 00);
        standard.set(Calendar.SECOND, 00);

        if(now.getTimeInMillis() < standard.getTimeInMillis()) {
            now.add(Calendar.DATE, -5);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dt = formatter.format(now.getTime());

        return mapper.selectRealConstrutionInfo(dt);
    }
    public List<CarSectionVO> selectRealConstrutionInfo(String date){
        return mapper.selectRealConstrutionInfo(date);
    }
    public void insertRealTrafficInfo(RealTrafficVO vo) {
        mapper.insertRealTrafficInfo(vo);
    }
    
    public List<RealTrafficVO> selectRealTrafficInfo(String date, String amount, String time) {
        return mapper.selectRealTrafficInfo(date, amount, time);
    }   
    public List<RegionalInfoVO> selectSidoInfo(String region) {
        return mapper.selectSidoInfo(region);
    }
    public List<RealTrafficVO> selectOption(String name){
        return mapper.selectOption(name);
    }
    
    public void insertRoadRiskIndex(RoadriskindexVO vo) {
        mapper.insertRoadRiskIndex(vo);
    }
    public List<RoadriskindexVO> selecthighwayName(String name) {
        return mapper.selecthighwayName(name);
    }
    public List<String> highwayName() {
        return mapper.highwayName();
    }
    public List<String> highwaySection(String highway) {
        return mapper.highwaySection(highway);
    }
    public List<String> selectLatitudeFrist(String highway, Integer grade, String highway_section) {
        return mapper.selectLatitudeFrist(highway, grade, highway_section);
    }
    public List<String> selectLongitudeFirst(String highway, Integer grade, String highway_section) {
        return mapper.selectLongitudeFirst(highway, grade, highway_section);
    }
    public List<String> selectLatitudeEnd(String highway, Integer grade, String highway_section) {
        return mapper.selectLatitudeEnd(highway, grade, highway_section);
    }
    // public List<String> selectLongitudeEnd(String highway, Integer grade, String highway_section) {
    //     return mapper.selectLongitudeEnd(highway, grade, highway_section);
    // }
    public List<RoadriskindexVO> selectLongitudeEnd(String highway, Integer grade, String highway_section) {
        return mapper.selectLongitudeEnd(highway, grade, highway_section);
    }
}


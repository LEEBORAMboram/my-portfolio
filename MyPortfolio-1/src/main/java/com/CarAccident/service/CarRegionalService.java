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
<<<<<<< HEAD
    public void insertRoadRiskIndex(RoadriskindexVO vo) {
        mapper.insertRoadRiskIndex(vo);
    }
=======
    public List<RealTrafficVO> selectOption(String name){
        return mapper.selectOption(name);
    }

    public void insertRoadRiskIndex(RoadriskindexVO vo) {
        mapper.insertRoadRiskIndex(vo);
    }
 }
>>>>>>> develop


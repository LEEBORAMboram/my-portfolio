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
    
    // public List<RealTrafficInfoVO> selectTodayRealTrafficInfo() {
    //     Calendar now = Calendar.getInstance();
    //     Calendar standard = Calendar.getInstance();
    //     standard.set(Calendar.HOUR_OF_DAY, 9);
    //     standard.set(Calendar.MINUTE, 30);
    //     standard.set(Calendar.SECOND, 00);
    //     if(now.getTimeInMillis() < standard.getTimeInMillis()) {
    //         now.add(Calendar.DATE, -1);
    //     }
    //     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    //     String dt = formatter.format(now.getTime());
    //     System.out.println(dt);

    //     return mapper.selectRealRegion(dt);
    // }
    public void insertCarSection(CarSectionVO vo) {
        mapper.insertCarSection(vo);
    }
    public void insertRealTrafficInfo(RealTrafficVO vo) {
        mapper.insertRealTrafficInfo(vo);
    }
    public List<RealTrafficVO> selectRealTodayTrafficInfo() {
        Calendar now = Calendar.getInstance();
        Calendar standard = Calendar.getInstance();
        standard.set(Calendar.HOUR_OF_DAY, 9);
        standard.set(Calendar.MINUTE, 30);
        standard.set(Calendar.SECOND, 00);
        if(now.getTimeInMillis() < standard.getTimeInMillis()) {
            now.add(Calendar.DATE, -1);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dt = formatter.format(now.getTime());
        System.out.println(dt);

        return mapper.selectRealTrafficInfo(dt);
    }
    public List<RealTrafficVO> selectRealTrafficInfo(String date) {
        return mapper.selectRealTrafficInfo(date);
    }   
    public List<RegionalInfoVO> selectSidoInfo(String region) {
        return mapper.selectSidoInfo(region);
    }
    public void insertRoadRiskIndex(RoadriskindexVO vo) {
        mapper.insertRoadRiskIndex(vo);
    }

}

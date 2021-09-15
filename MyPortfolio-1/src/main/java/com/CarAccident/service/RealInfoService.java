package com.CarAccident.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.CarAccident.mapper.RealInfoMapper;
import com.CarAccident.vo.RealInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RealInfoService {
    @Autowired
    RealInfoMapper mapper;

    public void insertRealInfo(RealInfoVO vo) {
        mapper.insertRealInfo(vo);
    }
    public List<RealInfoVO> selectRealtime(String spot) {
        return mapper.selectRealtime(spot);
    }
    public List<String> selectRegionNameCategory() {
        return mapper.selectRegionNameCategory();
    }
    public List<RealInfoVO> searchName(String search) {
        return mapper.searchName(search);
    }
    // public List<RealInfoVO> selectTodayInfo() {
    //     Calendar now = Calendar.getInstance();
    //     Calendar standard = Calendar.getInstance();
    //     standard.set(Calendar.HOUR_OF_DAY, 00);
    //     standard.set(Calendar.MINUTE, 00);
    //     standard.set(Calendar.SECOND, 00);

    //     if(now.getTimeInMillis() < standard.getTimeInMillis()) {
    //         now.add(Calendar.DATE, -1);
    //     }
    //     SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
    //     String dt = formatter.format(now.getTime());

    //     return mapper.selectrealSigungu(dt);
    // }
    public List<RealInfoVO> selectrealSigungu(String sigungu) {
        return mapper.selectrealSigungu(sigungu);
    }
}

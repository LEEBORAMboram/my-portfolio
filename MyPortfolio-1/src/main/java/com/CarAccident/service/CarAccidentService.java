package com.CarAccident.service;

import com.CarAccident.mapper.CarAccidentMapper;
import com.CarAccident.vo.CarAccidentVO;
import com.CarAccident.vo.RegionalInfoVO;
import com.CarAccident.vo.TrafficVolumeVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarAccidentService {
    @Autowired
    CarAccidentMapper mapper;

    public void selectCarAccidentInfo(CarAccidentVO vo) {
        mapper.selectCarAccidentInfo(vo);
    }

//     public CarAccidentVO selectTodayVictimInfo() {
        
//         Calendar now = Calendar.getInstance();
//         Calendar standard = Calendar.getInstance();
//         standard.set(Calendar.HOUR_OF_DAY, 18);
//         standard.set(Calendar.MINUTE, 00);
//         standard.set(Calendar.SECOND, 00);
        
//         if(now.getTimeInMillis() < standard.getTimeInMillis()) {
//             now.add(Calendar.DATE, -1);
//         }

//         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//         String dt = formatter.format(now.getTime());
//         // System.out.println(dt);

//         CarAccidentVO vo = Service.selectCarAccidentInfo();
// }
    public void selectRegionalInfo(RegionalInfoVO vo) {
            mapper.selectRegionalInfo(vo);
    }
    public void selectTrafficVolumeInfo(TrafficVolumeVO vo) {
        mapper.selectTrafficVolumeInfo(vo);
    }
}

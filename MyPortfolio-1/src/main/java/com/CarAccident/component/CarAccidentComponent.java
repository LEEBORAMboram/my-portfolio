package com.CarAccident.component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.CarAccident.service.CarRegionalService;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CarAccidentComponent {
    @Autowired
    CarRegionalService service2;
    
    // 매일 17시 30분에 한 번 실행
    @Scheduled(cron="0/5 * * * * *")
    public void getAccidentStatus() throws Exception {
     
  
    }
}

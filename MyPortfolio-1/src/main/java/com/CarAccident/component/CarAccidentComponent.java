package com.CarAccident.component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import com.CarAccident.service.CarAccidentService;
import com.CarAccident.vo.CarAccidentVO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CarAccidentComponent {
    @Autowired
    CarAccidentService service;
    
    // 매일 17시 30분에 한 번 실행
    @Scheduled(cron="0 30 17 * * *")
    public void getAccidentStatus() throws Exception {
      
        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15070293/v1/uddi:6d612d4d-82bc-4cc4-94d6-91795beda200");
        urlBuilder.append("?" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode("100000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "=tRlr59%2F4dQDnFxa4zgFhCjp8J33ZT%2BnEyJB4bcN4mMBoEKCCYCZ44RaQo4Dl6nt6qyMkUaRww8lWmJmNWYMFJg%3D%3D");
        //System.out.println(urlBuilder.toString());

        URL url = new URL(urlBuilder.toString());
        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();

        String line;
        while((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        System.out.println(sb.toString());

        //JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject(sb.toString());
        Integer cntObject = jsonObject.getInt("currentCount");
        System.out.println("갯수 : "+cntObject);
        
        JSONArray dataArray = jsonObject.getJSONArray("data");
        for(int i=0; i<dataArray.length(); i++) {
            JSONObject obj = dataArray.getJSONObject(i);
            Integer slightly = obj.getInt("경상자수");
            Integer injured = obj.getInt("부상신고자수");
            Integer death = obj.getInt("사망자수");
            String gender = obj.getString("사상자성별");
            String age = obj.getString("사상자연령층");
            Integer seriously = obj.getInt("중상자수");

            if(age.equals("12세이하")) age = "20세이하";
            else if(age.equals("13-20세")) age = "20세이하";
            // if(age.equals("21-30세")) age = "20대";
            // if(age.equals("31-40세")) age = "30대";
            // if(age.equals("41-50세")) age = "40대";
            // if(age.equals("51-60세")) age = "50대";
            // if(age.equals("61-70세")) age = "60대";

            CarAccidentVO vo = new CarAccidentVO();
            vo.setSlightly(slightly);
            vo.setInjured(injured);
            vo.setDeath(death);
            vo.setGender(gender);
            vo.setAge(age);
            vo.setSeriously(seriously);

            service.selectCarAccidentInfo(vo);  
        }
    }
}

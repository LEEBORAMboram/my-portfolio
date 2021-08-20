package com.CarAccident.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import com.CarAccident.service.CarAccidentService;
import com.CarAccident.vo.CarAccidentVO;
import com.CarAccident.vo.RegionalInfoVO;
import com.CarAccident.vo.TrafficVolumeVO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccidentAPIController {
    @Autowired
    CarAccidentService service;

    @GetMapping("/api/accident/victim2020")
    public Map<String, Object> getAccidentStatus() throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15070293/v1/uddi:6d612d4d-82bc-4cc4-94d6-91795beda200");
        urlBuilder.append("?" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode("10000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "=tRlr59%2F4dQDnFxa4zgFhCjp8J33ZT%2BnEyJB4bcN4mMBoEKCCYCZ44RaQo4Dl6nt6qyMkUaRww8lWmJmNWYMFJg%3D%3D");
        // urlBuilder.append("&" + URLEncoder.encode("startDt", "UTF-8") + "=" + URLEncoder.encode(startDt, "UTF-8"));
        // urlBuilder.append("&" + URLEncoder.encode("endDt", "UTF-8") + "=" + URLEncoder.encode(endDt, "UTF-8"));
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
            if(gender.equals("기타불명")) gender = "불명";
            else if(gender.equals("기타/불명")) gender = "불명";

            CarAccidentVO vo = new CarAccidentVO();
            vo.setSlightly(slightly);
            vo.setInjured(injured);
            vo.setDeath(death);
            vo.setGender(gender);
            vo.setAge(age);
            vo.setSeriously(seriously);

            service.selectCarAccidentInfo(vo);            
            // System.out.println(slightly);
            // System.out.println(injured);
            // System.out.println(death);
            // System.out.println(gender);
            // System.out.println(age);
            // System.out.println(seriously);
            System.out.println("====================================");
        }
        resultMap.put("status", true);

        return resultMap;
    }
    // @GetMapping("/api/accident/victim/{date}")
    // public Map<String, Object> getAccidentVictim(@PathVariable String date) {
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    //     if(date.equals("today")) {
    //         List<CarAccidentVO> list = service.selectCarAccidentInfo();
    //         resultMap.put("data", list);
    //         return resultMap;
    //     }

    //     List<CarAccidentVO> list = service.sele
    // }
    @GetMapping("/api/accident/victim2019")
    public Map<String, Object> getAccidentVictim() throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15070293/v1/uddi:c9a1fdfe-5fa8-4662-bc11-a5ec9eaded1e");
        urlBuilder.append("?" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode("10000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "=tRlr59%2F4dQDnFxa4zgFhCjp8J33ZT%2BnEyJB4bcN4mMBoEKCCYCZ44RaQo4Dl6nt6qyMkUaRww8lWmJmNWYMFJg%3D%3D");
        // urlBuilder.append("&" + URLEncoder.encode("startDt", "UTF-8") + "=" + URLEncoder.encode(startDt, "UTF-8"));
        // urlBuilder.append("&" + URLEncoder.encode("endDt", "UTF-8") + "=" + URLEncoder.encode(endDt, "UTF-8"));
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
            String age = obj.getString("사상자연령");
            Integer seriously = obj.getInt("중상자수");

            if(age.equals("12세이하")) age = "20세이하";
            else if(age.equals("13-20세")) age = "20세이하";
            if(gender.equals("기타불명")) gender = "불명";
            else if(gender.equals("기타/불명")) gender = "불명";

            CarAccidentVO vo = new CarAccidentVO();
            vo.setSlightly(slightly);
            vo.setInjured(injured);
            vo.setDeath(death);
            vo.setGender(gender);
            vo.setAge(age);
            vo.setSeriously(seriously);

            service.selectCarAccidentInfo(vo);            
            // System.out.println(slightly);
            // System.out.println(injured);
            // System.out.println(death);
            // System.out.println(gender);
            // System.out.println(age);
            // System.out.println(seriously);
            System.out.println("====================================");
        }
        resultMap.put("status", true);

        return resultMap;
    }
    @GetMapping("/api/accident/victim2018")
    public Map<String, Object> getAccidentVictim3() throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15070293/v1/uddi:01c9975f-40d0-45ef-bc7e-fd630ea8bc9f");
        urlBuilder.append("?" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode("10000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "=tRlr59%2F4dQDnFxa4zgFhCjp8J33ZT%2BnEyJB4bcN4mMBoEKCCYCZ44RaQo4Dl6nt6qyMkUaRww8lWmJmNWYMFJg%3D%3D");
        // urlBuilder.append("&" + URLEncoder.encode("startDt", "UTF-8") + "=" + URLEncoder.encode(startDt, "UTF-8"));
        // urlBuilder.append("&" + URLEncoder.encode("endDt", "UTF-8") + "=" + URLEncoder.encode(endDt, "UTF-8"));
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
            String age = obj.getString("사상자연령");
            Integer seriously = obj.getInt("중상자수");

            if(age.equals("12세이하")) age = "20세이하";
            else if(age.equals("13-20세")) age = "20세이하";
            if(gender.equals("기타불명")) gender = "불명";
            else if(gender.equals("기타/불명")) gender = "불명";
            
            CarAccidentVO vo = new CarAccidentVO();
            vo.setSlightly(slightly);
            vo.setInjured(injured);
            vo.setDeath(death);
            vo.setGender(gender);
            vo.setAge(age);
            vo.setSeriously(seriously);

            service.selectCarAccidentInfo(vo);            
            // System.out.println(slightly);
            // System.out.println(injured);
            // System.out.println(death);
            // System.out.println(gender);
            // System.out.println(age);
            // System.out.println(seriously);
            System.out.println("====================================");
        }
        resultMap.put("status", true);

        return resultMap;
    }
    @GetMapping("/api/accident/victim2017")
    public Map<String, Object> getAccidentVictim4() throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15070293/v1/uddi:a3212a26-7aa5-45c2-8737-e3145246b66b");
        urlBuilder.append("?" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode("10000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "=tRlr59%2F4dQDnFxa4zgFhCjp8J33ZT%2BnEyJB4bcN4mMBoEKCCYCZ44RaQo4Dl6nt6qyMkUaRww8lWmJmNWYMFJg%3D%3D");
        // urlBuilder.append("&" + URLEncoder.encode("startDt", "UTF-8") + "=" + URLEncoder.encode(startDt, "UTF-8"));
        // urlBuilder.append("&" + URLEncoder.encode("endDt", "UTF-8") + "=" + URLEncoder.encode(endDt, "UTF-8"));
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
            String age = obj.getString("사상자연령");
            Integer seriously = obj.getInt("중상자수");

            if(age.equals("12세이하")) age = "20세이하";
            else if(age.equals("13-20세")) age = "20세이하";
            if(gender.equals("기타불명")) gender = "불명";
            else if(gender.equals("기타/불명")) gender = "불명";
            
            CarAccidentVO vo = new CarAccidentVO();
            vo.setSlightly(slightly);
            vo.setInjured(injured);
            vo.setDeath(death);
            vo.setGender(gender);
            vo.setAge(age);
            vo.setSeriously(seriously);

            service.selectCarAccidentInfo(vo);            
            // System.out.println(slightly);
            // System.out.println(injured);
            // System.out.println(death);
            // System.out.println(gender);
            // System.out.println(age);
            // System.out.println(seriously);
            System.out.println("====================================");
        }
        resultMap.put("status", true);

        return resultMap;
    }
    @GetMapping("/api/accident/regional")
    public Map<String, Object> getAccidentRegional() throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15070295/v1/uddi:66b562c4-1e81-494e-ac40-d5b9520eb898");
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

        //System.out.println(sb.toString());

        //JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = new JSONObject(sb.toString());
        Integer cntObject = jsonObject.getInt("currentCount");
        System.out.println("갯수 : "+cntObject);
        
        JSONArray dataArray = jsonObject.getJSONArray("data");
        for(int i=0; i<dataArray.length(); i++) {
        JSONObject obj = dataArray.getJSONObject(i);
        Integer slightly = obj.getInt("경상자수");
        Integer month = obj.getInt("발생월");
        Integer injured = obj.getInt("부상신고자수");
        Integer acc_cnt = obj.getInt("사고건수");
        Integer death = obj.getInt("사망자수");
        String sigungu = obj.getString("시군구");
        String sido = obj.getString("시도");
        Integer seriously = obj.getInt("중상자수");
    
        RegionalInfoVO vo = new RegionalInfoVO();
        vo.setRegion_slightly(slightly);
        vo.setRegion_month(month);  
        vo.setRegion_injured(injured);  
        vo.setRegion_acc_cnt(acc_cnt);  
        vo.setRegion_death(death);  
        vo.setRegion_sigungu(sigungu);  
        vo.setRegion_sido(sido);  
        vo.setRegion_seriously(seriously);      
        
        service.selectRegionalInfo(vo);
        // System.out.println(slightly);
        // System.out.println(month);
        // System.out.println(injured);
        // System.out.println(acc_cnt);
        // System.out.println(death);
        // System.out.println(sigungu);
        // System.err.println(sido);
        // System.out.println(seriously);
        // System.out.println("=======================================================");
        }
        resultMap.put("status", true);

        return resultMap;
    }
    @GetMapping("/api/accident/volume")
    public Map<String, Object> getAccidentVolume() throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/trafficapi/trafficAll");
        urlBuilder.append("?" + URLEncoder.encode("key", "UTF-8") + "=0756416339");
        urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        System.out.println(urlBuilder.toString());

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
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

        JSONObject jsonObject = new JSONObject(sb.toString());
        Integer cntObject = jsonObject.getInt("count");
        System.out.println("갯수 : "+cntObject);
        
        JSONArray dataArray = jsonObject.getJSONArray("trafficAll");
        for(int i=0; i<dataArray.length(); i++) {
            JSONObject obj = dataArray.getJSONObject(i);
            String tcsName = obj.getString("tcsName");
            Integer trafficAmout = obj.getInt("trafficAmout");
            Integer sumTime = obj.getInt("sumTm");     
            
            TrafficVolumeVO vo = new TrafficVolumeVO();
            vo.setTcsName(tcsName);
            vo.setTrafficAmout(trafficAmout);
            vo.setSumTime(sumTime);

            service.selectTrafficVolumeInfo(vo);
        }
        resultMap.put("status", true);
        return resultMap;
    }
}
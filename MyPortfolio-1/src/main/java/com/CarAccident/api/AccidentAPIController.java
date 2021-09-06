package com.CarAccident.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.CarAccident.service.CarAccidentService;
import com.CarAccident.service.CarRegionalService;
import com.CarAccident.service.TheOthersService;
import com.CarAccident.vo.CarAccidentVO;
import com.CarAccident.vo.CarSectionVO;
import com.CarAccident.vo.RealTrafficVO;
import com.CarAccident.vo.RegionalInfoVO;
import com.CarAccident.vo.TemperatureVO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@RestController
public class AccidentAPIController {
    @Autowired
    CarAccidentService service;
    @Autowired
    CarRegionalService service2;
    @Autowired
    TheOthersService service3;

    @GetMapping("/api/accident/victim2020")
    public Map<String, Object> getAccidentStatus() throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15070293/v1/uddi:6d612d4d-82bc-4cc4-94d6-91795beda200");
        urlBuilder.append("?" + URLEncoder.encode("page", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("perPage", "UTF-8") + "=" + URLEncoder.encode("10000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "=tRlr59%2F4dQDnFxa4zgFhCjp8J33ZT%2BnEyJB4bcN4mMBoEKCCYCZ44RaQo4Dl6nt6qyMkUaRww8lWmJmNWYMFJg%3D%3D");


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

            service.insertCarAccidentInfo(vo);            
            System.out.println("====================================");
        }
        resultMap.put("status", true);

        return resultMap;
    }
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

            service.insertCarAccidentInfo(vo);            
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

            service.insertCarAccidentInfo(vo);            
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

            service.insertCarAccidentInfo(vo);            
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
        
        service.insertRegionalInfo(vo);
        }
        resultMap.put("status", true);

        return resultMap;
    }
    @GetMapping("/api/accident/regional/chart")
    public Map<String, Object> getRegionalInfo(@RequestParam String region){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        List<RegionalInfoVO> data = null;
        data = service2.selectSidoInfo(region);
    
        resultMap.put("status", true);
        resultMap.put("data", data);

        return resultMap;
    }
    @GetMapping("/api/temperature")
    public Map<String, Object> getTemperature() throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/AsosDalyInfoService/getWthrDataList");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=tRlr59%2F4dQDnFxa4zgFhCjp8J33ZT%2BnEyJB4bcN4mMBoEKCCYCZ44RaQo4Dl6nt6qyMkUaRww8lWmJmNWYMFJg%3D%3D");
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("999", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("XML", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("dataCd", "UTF-8") + "=" + URLEncoder.encode("ASOS", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("dateCd", "UTF-8") + "=" + URLEncoder.encode("DAY", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("startDt", "UTF-8") + "=" + URLEncoder.encode("20200101", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("endDt", "UTF-8") + "=" + URLEncoder.encode("20201231", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("stnIds", "UTF-8") + "=" + URLEncoder.encode("108", "UTF-8"));
        
        System.out.println(urlBuilder.toString());

        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(urlBuilder.toString());

      
        doc.getDocumentElement().normalize();
        System.out.println(doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("item");
        System.out.println("데이터 수 : "+nList.getLength());

        for(int i=0; i<nList.getLength(); i++) {
            Node n = nList.item(i);
            Element elem = (Element)n;

            String time = getTagValue("tm", elem);
            String rainfall = getTagValue("sumRn", elem);
            String wind = getTagValue("avgWs", elem);
            String temp = getTagValue("avgTa", elem);
            System.out.println("=========================================");

            TemperatureVO vo = new TemperatureVO();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = formatter.parse(time);

            if(rainfall == null) {
                rainfall = "0";
            }

            vo.setTm(dt);
            vo.setRainfall(Double.parseDouble(rainfall));
            vo.setWind(Double.parseDouble(wind));
            vo.setTemp(Double.parseDouble(temp));

            service.inserttemperatureInfo(vo);

        }
            return resultMap;
    }
    
        
    @GetMapping("/api/accident/underconstruction")
    public Map<String, Object> getUnderconstruction() throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        StringBuilder urlBuilder = new StringBuilder("https://openapi.its.go.kr:9443/eventInfo");
        urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=7a5ffcb7e1bf4c368ed8cec6b125721f");
        urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("all", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("eventType", "UTF-8") + "=" + URLEncoder.encode("all", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("minX", "UTF-8") + "=" + URLEncoder.encode("126.800000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("maxX", "UTF-8") + "=" + URLEncoder.encode("127.890000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("minY", "UTF-8") + "=" + URLEncoder.encode("34.900000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("maxY", "UTF-8") + "=" + URLEncoder.encode("35.100000", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("getType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
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

        JSONObject jsonObject = new JSONObject(sb.toString());
        JSONObject bodyObj = (JSONObject) jsonObject.get("body");     
        //JSONObject itemsObj = (JSONObject) bodyObj.get("items"); 

        JSONArray itemsArr = bodyObj.getJSONArray("items");
        for(int i=0; i<itemsArr.length(); i++) {
            JSONObject obj = itemsArr.getJSONObject(i);
            String road_type = obj.getString("type");
            String eventType = obj.getString("eventType");
            String eventDetailType = obj.getString("eventDetailType");   
            String startDate = obj.getString("startDate");
            String roadName = obj.getString("roadName");
            String roadDrcType = obj.getString("roadDrcType"); 
            String lanesBlockType = obj.getString("lanesBlockType");
            String endDt = obj.getString("endDate");

       
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date dt = sdf.parse(startDate);
            Date ddt = sdf.parse(endDt);

            CarSectionVO vo = new CarSectionVO();
            vo.setRoad_type(road_type);
            vo.setEventType(eventType);
            vo.setEventDetailType(eventDetailType);
            vo.setStartDate(dt);;
            vo.setRoadName(roadName);
            vo.setRoadDrcType(roadDrcType);
            vo.setLanesBlockType(lanesBlockType);
            vo.setEndDt(ddt);

            service2.insertCarSection(vo);
        }

        resultMap.put("status", true);
        return resultMap;
    }
    @GetMapping("/api/Real/{date}")
    public Map<String, Object> getConstructionInfoByDate(
        @PathVariable String date) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(date.equals("today")) {
            List<CarSectionVO> list = service2.selectRealTodayConstrunction();
            resultMap.put("status", true);
            resultMap.put("data", list);
        }
        else {
            List<CarSectionVO> list = service2.selectRealConstrutionInfo(date);
            resultMap.put("status", true);
            resultMap.put("data", list);
        }
        return resultMap;
    }
    @GetMapping("/api/accident/real_traffic")
    public Map<String, Object> getRealTraffic() throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        StringBuilder urlBuilder = new StringBuilder("http://data.ex.co.kr/openapi/odtraffic/trafficAmountByRealtime");
        urlBuilder.append("?" + URLEncoder.encode("key", "UTF-8") + "=9484350634");
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

        //System.out.println(sb.toString());
        JSONObject jsonObject = new JSONObject(sb.toString());
        Integer cntObject = jsonObject.getInt("count");
        System.out.println("갯수 : "+cntObject);
 
        JSONArray itemsArr = jsonObject.getJSONArray("list");
        for(int i=0; i<itemsArr.length(); i++) {
            JSONObject obj = itemsArr.getJSONObject(i);
            String start_hour = obj.getString("stdHour");
            String routeName = obj.getString("routeName");
            String traffic_volume = obj.getString("trafficAmout");
            String shareRatio = obj.getString("shareRatio");
            String conzoneName = obj.getString("conzoneName");
            String stdDate = obj.getString("stdDate");
            String speed = obj.getString("speed");
            String timeAvg = obj.getString("timeAvg");

            // DecimalFormat dFormatter = new DecimalFormat("##:##");
            // String str_hour = dFormatter.format(start_hour);

            // 앞쪽 두 글자:뒤쪽 두 글자
            String str_hour = start_hour.substring(0, 2)+":"+start_hour.substring(2, 4);

            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date dt2 = format.parse(stdDate);

           RealTrafficVO vo = new RealTrafficVO();
           vo.setStart_hour(str_hour);
           vo.setRouteName(routeName);
           vo.setTraffic_volume(traffic_volume);
           vo.setShareRatio(shareRatio);
           vo.setConzoneName(conzoneName);
           vo.setStdDate(dt2);
           vo.setSpeed(speed);
           vo.setTimeAvg(timeAvg);

           service2.insertRealTrafficInfo(vo);
        }
        resultMap.put("status", true);
        return resultMap;
    }
    @GetMapping("/api/realTrafficInfo/{date}/{time}/{amount}")
    public Map<String, Object> getRealTrafficInfo(@PathVariable String date, @PathVariable String time, @PathVariable String amount) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(date.equals("today")) {
            List<RealTrafficVO> list = service2.selectRealTrafficInfo(date, time, amount);
            resultMap.put("status", true);
            resultMap.put("data", list);
        }
            resultMap.put("status", false);
            return resultMap;
        }
    @GetMapping("api/routename/{name}")
    public Map<String, Object> getROuteAameInfo(@PathVariable String name) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<RealTrafficVO> list = service2.selectOption(name);
        return resultMap;
    }
    public static String getTagValue(String tag, Element elem) {
        NodeList nlList = elem.getElementsByTagName(tag).item(0).getChildNodes();
        if(nlList == null) return null;
        Node node = (Node) nlList.item(0);
        if(node == null) return null;
        return node.getNodeValue();
    }
}
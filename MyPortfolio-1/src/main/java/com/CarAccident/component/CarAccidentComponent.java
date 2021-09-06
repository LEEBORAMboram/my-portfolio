package com.CarAccident.component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.CarAccident.service.CarRegionalService;
import com.CarAccident.vo.RealTrafficVO;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CarAccidentComponent {
    @Autowired
    CarRegionalService service2;
    
    // 5분에 한 번씩
    @Scheduled(cron="* */5 * * * *")
    public void getRealStatus() throws Exception {
        // 이거는 계속 에러 뜨고 있고.
        // Date dt = new Date();
        // SimpleDateFormat dtFormatter = new SimpleDateFormat("yyyyMMdd");
        // String today = dtFormatter.format(dt);

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
    }
    // @Scheduled(cron="00 00 10 * * *")
    // public void getConstructionInfoByDate() throws Exception {
    // StringBuilder urlBuilder = new StringBuilder("https://openapi.its.go.kr:9443/eventInfo");
    //     urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=7a5ffcb7e1bf4c368ed8cec6b125721f");
    //     urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("all", "UTF-8"));
    //     urlBuilder.append("&" + URLEncoder.encode("eventType", "UTF-8") + "=" + URLEncoder.encode("all", "UTF-8"));
    //     urlBuilder.append("&" + URLEncoder.encode("minX", "UTF-8") + "=" + URLEncoder.encode("126.800000", "UTF-8"));
    //     urlBuilder.append("&" + URLEncoder.encode("maxX", "UTF-8") + "=" + URLEncoder.encode("127.890000", "UTF-8"));
    //     urlBuilder.append("&" + URLEncoder.encode("minY", "UTF-8") + "=" + URLEncoder.encode("34.900000", "UTF-8"));
    //     urlBuilder.append("&" + URLEncoder.encode("maxY", "UTF-8") + "=" + URLEncoder.encode("35.100000", "UTF-8"));
    //     urlBuilder.append("&" + URLEncoder.encode("getType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
    //     System.out.println(urlBuilder.toString());

    //     URL url = new URL(urlBuilder.toString());
    //     HttpURLConnection conn = (HttpURLConnection)url.openConnection();
    //     conn.setRequestMethod("GET");
    //     conn.setRequestProperty("Content-type", "application/json");

    //     BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    //     StringBuilder sb = new StringBuilder();

    //     String line;
    //     while((line = rd.readLine()) != null) {
    //         sb.append(line);
    //     }
    //     rd.close();
    //     conn.disconnect();

    //     JSONObject jsonObject = new JSONObject(sb.toString());
    //     JSONObject bodyObj = (JSONObject) jsonObject.get("body");     
    //     //JSONObject itemsObj = (JSONObject) bodyObj.get("items"); 

    //     JSONArray itemsArr = bodyObj.getJSONArray("items");
    //     for(int i=0; i<itemsArr.length(); i++) {
    //         JSONObject obj = itemsArr.getJSONObject(i);
    //         String road_type = obj.getString("type");
    //         String eventType = obj.getString("eventType");
    //         String eventDetailType = obj.getString("eventDetailType");   
    //         String startDate = obj.getString("startDate");
    //         String roadName = obj.getString("roadName");
    //         String roadDrcType = obj.getString("roadDrcType"); 
    //         String lanesBlockType = obj.getString("lanesBlockType");
    //         //String endDt = obj.getString("endDate");

       
    //         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    //         Date dt = sdf.parse(startDate);
    //         //Date ddt = sdf.parse(endDt);

    //         CarSectionVO vo = new CarSectionVO();
    //         vo.setRoad_type(road_type);
    //         vo.setEventType(eventType);
    //         vo.setEventDetailType(eventDetailType);
    //         vo.setStartDate(dt);;
    //         vo.setRoadName(roadName);
    //         vo.setRoadDrcType(roadDrcType);
    //         vo.setLanesBlockType(lanesBlockType);
    //         //vo.setEndDt(ddt);

    //         service2.insertCarSection(vo);
    //     }
    // }   
}
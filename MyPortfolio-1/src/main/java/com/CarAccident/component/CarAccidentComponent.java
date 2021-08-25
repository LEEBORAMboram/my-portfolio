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
}

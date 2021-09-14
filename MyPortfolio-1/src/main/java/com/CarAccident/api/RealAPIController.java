package com.CarAccident.api;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.CarAccident.service.CarAccidentService;
import com.CarAccident.service.CarRegionalService;
import com.CarAccident.service.RealInfoService;
import com.CarAccident.vo.RealInfoVO;
import com.CarAccident.vo.RealTrafficVO;
import com.CarAccident.vo.RoadriskindexVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@RestController
public class RealAPIController {
    @Autowired
    CarAccidentService service;
    @Autowired
    CarRegionalService service2;
    @Autowired
    RealInfoService service3;

    @GetMapping("/api/real")
    public Map<String, Object> getRealHighwayStatusInfo(
        @RequestParam @Nullable String amount,
        @RequestParam @Nullable String date,
        @RequestParam @Nullable String time) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    
        // if(amount == null) {
        //     amount = ''
        // }
        if(date == null || date.equals("")) {
            Calendar now = Calendar.getInstance();
            Calendar standard = Calendar.getInstance();
            standard.set(Calendar.HOUR_OF_DAY, 9);
            standard.set(Calendar.MINUTE, 00);
            standard.set(Calendar.SECOND, 00);

            if(now.getTimeInMillis() < standard.getTimeInMillis()) {
                now.add(Calendar.DATE, -1);
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            date = formatter.format(now.getTime());
        }
        
        amount = amount.substring(0, 2)+":"+amount.substring(2, 4);

        List<RealTrafficVO> realtrafficlist = service2.selectRealTrafficInfo(date, amount, time);
        resultMap.put("realtrafficlist", realtrafficlist);

        return resultMap;
    }

    @GetMapping("/api/real/roadriskindex")
    public Map<String, Object> getRoadRiskIndexInfo() throws Exception {

        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        StringBuilder urlBuilder = new StringBuilder("http://taas.koroad.or.kr/data/rest/road/dgdgr/highway");
        urlBuilder.append("?" + URLEncoder.encode("authKey", "UTF-8") + "=JzEOb628nl7APcar0CQWRlccfLeIVdvb6vFVXvHO9QvLdn2PBbLjg%2FN41BPFY%2BEy");
        urlBuilder.append("&" + URLEncoder.encode("frwyNm", "UTF-8") + "=" + URLEncoder.encode("당진영덕고속도로", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("frwySctnNm", "UTF-8") + "=" + URLEncoder.encode("낙동JC-상주JC", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("vhctyCd", "UTF-8") + "=" + URLEncoder.encode("01", "UTF-8"));
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

            String road_index = getTagValue("index", elem);
            String line_string = getTagValue("line_string", elem);
            String anals_value = getTagValue("anals_value", elem);
            String anals_grd = getTagValue("anals_grd", elem);
            // String highway = getTagValue("경부고속도로", elem);
            // String highway_section = getTagValue("건천IC-경주IC", elem);
            System.out.println("==============================================");
        
            // if(line_string.equals("("))

            RoadriskindexVO vo = new RoadriskindexVO();

            // vo.setHighway(highway);
            // vo.setHighway_section(highway_section);
            vo.setRoad_index(Integer.parseInt(road_index));
            vo.setLine_string(line_string);
            vo.setAnals_value(Double.parseDouble(anals_value));
            vo.setAnals_grd(Integer.parseInt(anals_grd));

            service2.insertRoadRiskIndex(vo);
        }
        return resultMap;
    }

    @GetMapping("/api/highway/nameinfo")
    public Map<String, Object> getHighwayNameInfo(
        @RequestParam String name
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        //"경부고속도로" => ['경','부','고','속','도','로']
        char[] c = name.toCharArray();
        name = "%"+c[0]+"%"+c[1]+"%"+c[2]+"%"+c[3]+"%"+c[4]+"%"+c[5]+"%";

        List<RoadriskindexVO> highwayname = service2.selecthighwayName(name);
        resultMap.put("highwayname", highwayname);

        resultMap.put("status", true);
        return resultMap;
    }

    @GetMapping("/api/highwayName")
    public List<String> getHighwayName() {
       return service2.highwayName();
    }   
    @GetMapping("/api/highwaySection")
    public List<String> getHighwaySection(@RequestParam String highway) {
       return service2.highwaySection(highway);
    }
    @GetMapping("/api/section/highwayAdress")
    public Map<String, Object> getgetget(@RequestParam String highway, @RequestParam Integer grade,@RequestParam String highway_section) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
       
        // if(selectbox == null) selectbox = "%%";
        // else         selectbox = selectbox+"%";
        //    char[] c = selectbox.toCharArray();
        //    selectbox = "%"+c[0]+"%"+c[1]+"%"+c[2]+"%"+c[3]+"%"+c[4]+"%";

       List<RoadriskindexVO> getget = service2.selectLongitudeEnd(highway, grade, highway_section);
       resultMap.put("getget", getget);

       resultMap.put("status", true);
       return resultMap;
    }
    @GetMapping("/api/realInformation")
    public Map<String, Object> getRealInfo() throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        StringBuilder urlBuilder = new StringBuilder("https://www.utic.go.kr:449/guide/imsOpenData.do");
        urlBuilder.append("?" + URLEncoder.encode("key", "UTF-8") + "=vGavc8jD1zgF2ITwweTCcHqdGAu7f2wNgNGqJVvO4svGot0XoLtGbVr0XY0D6Yhw");
        
        System.out.println(urlBuilder.toString());
        
        DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(urlBuilder.toString());

      
        doc.getDocumentElement().normalize();
        System.out.println(doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("record");
        System.out.println("데이터 수 : "+nList.getLength());

        for(int i=0; i<nList.getLength(); i++) {
            Node n = nList.item(i);
            Element elem = (Element)n;

            String addressJibun = getTagValue("addressJibun", elem); // 도로명주소
            String locationDataX = getTagValue("locationDataX", elem);  // x좌표
            String locationDataY = getTagValue("locationDataY", elem);  // y좌표
            String incidentTitle = getTagValue("incidentTitle", elem);  // 돌발제목
            String startDate = getTagValue("startDate", elem);  // 발생일
            String lane = getTagValue("lane", elem);    // 차선
            System.out.println(startDate);

            RealInfoVO vo = new RealInfoVO();

            Date dt = new Date();
            SimpleDateFormat dfFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분"); 
            dt = dfFormat.parse(startDate);


            vo.setAddressJibun(addressJibun);
            vo.setLocationDataX(Double.parseDouble(locationDataX));
            vo.setLocationDataY(Double.parseDouble(locationDataY));
            vo.setIncidentTitle(incidentTitle);
            vo.setStartDate(dt);
            vo.setLane(lane);
            
            service3.insertRealInfo(vo);

        }
        return resultMap;
    }
    @GetMapping("/api/realtimeInformation")
    public Map<String, Object> getRealTimeInfo(@RequestParam String region) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        //"대구광역시" => ['대','구','광','역','시']
        char[] c = region.toCharArray();
        region = "%"+c[0]+"%"+c[1]+"%"+c[2]+"%"+c[3]+"%"+c[4]+"%";

        List<RealInfoVO> realtimesido = service3.selectRealtime(region);
        resultMap.put("realtimesido", realtimesido);

        resultMap.put("status", true);
        return resultMap;
        
    }
    @GetMapping("/api/city")
    public List<String> getCityRegions() {
        
        return service3.selectRegionNameCategory();
    }
    @GetMapping("/api/city/sigungu")
        public Map<String, Object> getRealtimesigungu(@RequestParam String search
        ) {
            Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

            if(search == null || search == "") search = "%%";
            else search = "%"+search+"%";
          
            // char[] c = search.toCharArray();
            // search = "%"+c[0]+"%"+c[1]+"%"+c[2]+"%"+c[3]+"%"+c[4]+"%";

            List<RealInfoVO> citysigungu = service3.selectrealSigungu(search);
            resultMap.put("citysigungu", citysigungu);

            resultMap.put("status", true);

            return resultMap;

    }
    // @GetMapping("/api/searchName")
    // public Map<String, Object>searchName(@RequestParam @Nullable String search) throws Exception {
    //     Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

    //     if(search == null || search == "") search = "%%";
    //     else search = "%"+search+"%";

    //     List<RealInfoVO> searchname = service3.searchName(search);
    //     resultMap.put("searchname", searchname);

    //     resultMap.put("status", true);
    //     return resultMap;
    // } 

    @GetMapping("/api/contentRealInfo")
    public Map<String, Object>searchContent(@RequestParam @Nullable String search
    ) throws Exception {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        // if(date == null || date.equals("")) {
        //     Date now = new Date();
                    
            // Calendar now = Calendar.getInstance();
            // Calendar standard = Calendar.getInstance();
            // standard.set(Calendar.HOUR_OF_DAY, 00);
            // standard.set(Calendar.MINUTE, 00);
            // standard.set(Calendar.SECOND, 00);

            // if(now.getTimeInMillis() < standard.getTimeInMillis()) {
            //     now.add(Calendar.DATE, -1);
            // }
            // if (diff.equals("0")) diff = "조금 전";
            // else if(diff == "-1") diff ="1시간 전";
            // else if(diff == "-2") diff ="2시간 전";
            // else if(diff == "-3") diff ="3시간 전";
            // else if(diff == "-4") diff ="4시간 전";
            // else if(diff == "-5") diff ="5시간 전";
            // else if(diff == "-6") diff ="6시간 전";
            // else if(diff == "-7") diff ="7시간 전";
            // else if(diff == "-8") diff ="8시간 전";
            // else if(diff == "-9") diff ="9시간 전";
            // else if(diff == "-10") diff ="10시간 전";
            // else if(diff == "-11") diff ="11시간 전";
            // else if(diff == "-12") diff ="12시간 전";
            // else if(diff == "-13") diff ="13시간 전";
            // else if(diff == "-14") diff ="14시간 전";
            // else if(diff == "-15") diff ="15시간 전";
            // else if(diff == "-16") diff ="16시간 전";
            // else if(diff == "-17") diff ="17시간 전";
            // else if(diff == "-18") diff ="18시간 전";
            // else if(diff == "-19") diff ="19시간 전";
            // else if(diff == "-20") diff ="20시간 전";
            // else if(diff == "-21") diff ="21시간 전";
            // else if(diff == "-22") diff ="22시간 전";
            // else if(diff == "-23") diff ="23시간 전";
            // else if(diff == "-24") diff ="24시간 전";

            if(search == null || search == "") search = "%%";
            else search = "%"+search+"%";

            List<RealInfoVO> contentList = service3.searchName(search);
            resultMap.put("contentList", contentList);
        
        
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

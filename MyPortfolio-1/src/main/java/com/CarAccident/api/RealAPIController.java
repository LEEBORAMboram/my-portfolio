package com.CarAccident.api;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.CarAccident.service.CarAccidentService;
import com.CarAccident.service.CarRegionalService;
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
        urlBuilder.append("&" + URLEncoder.encode("frwyNm", "UTF-8") + "=" + URLEncoder.encode("서울외곽순환고속도로", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("frwySctnNm", "UTF-8") + "=" + URLEncoder.encode("호원IC-호원IC", "UTF-8"));
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
    public static String getTagValue(String tag, Element elem) {
        NodeList nlList = elem.getElementsByTagName(tag).item(0).getChildNodes();
        if(nlList == null) return null;
        Node node = (Node) nlList.item(0);
        if(node == null) return null;
        return node.getNodeValue();
    }
}

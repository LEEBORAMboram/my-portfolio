package com.CarAccident.component;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.CarAccident.service.RealInfoService;
import com.CarAccident.vo.RealInfoVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Component
public class CarAccidentComponent {
    @Autowired
    RealInfoService service;
    
    // 5분에 한 번씩
    @Scheduled(cron="* */5 * * * *")
    public void getRealTimeInfo() throws Exception {
        Date d = new Date();
        SimpleDateFormat dtformatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
        String today = dtformatter.format(d);

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

            RealInfoVO vo = new RealInfoVO();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분");
            Date  dt = formatter.parse(startDate);


            vo.setAddressJibun(addressJibun);
            vo.setLocationDataX(Double.parseDouble(locationDataX));
            vo.setLocationDataY(Double.parseDouble(locationDataY));
            vo.setIncidentTitle(incidentTitle);
            vo.setStartDate(dt);
            vo.setLane(lane);
            
            service.insertRealInfo(vo);    
        }
    }
        public static String getTagValue(String tag, Element elem) {
            NodeList nlList = elem.getElementsByTagName(tag).item(0).getChildNodes();
            if(nlList == null) return null;
            Node node = (Node) nlList.item(0);
            if(node == null) return null;
            return node.getNodeValue();
    }

}
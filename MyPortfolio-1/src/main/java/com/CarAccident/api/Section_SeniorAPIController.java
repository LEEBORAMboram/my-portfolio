package com.CarAccident.api;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.CarAccident.service.Section_SeniorService;
import com.CarAccident.vo.Section_SeniorVO;

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
public class Section_SeniorAPIController {
    @Autowired
    Section_SeniorService service;
    
    @GetMapping("/api/section_senior")
    public Map<String, Object> getSectionSenior() throws Exception {
        Map<String, Object> resultMap =  new LinkedHashMap<String, Object>();

        StringBuilder urlBuilder = new StringBuilder("http://taas.koroad.or.kr/data/rest/frequentzone/oldman");
        urlBuilder.append("?" + URLEncoder.encode("authKey", "UTF-8") + "=AWbYhBHpbyPY1uQoKsbLlc7y2CqCa8WTRuomhehJuW9cuSyHY0QqrprRFtnjsckZ");
        urlBuilder.append("&" + URLEncoder.encode("searchYearCd", "UTF-8") + "=" + URLEncoder.encode("2021024", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("siDo", "UTF-8") + "=" + URLEncoder.encode("50", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("guGun", "UTF-8") + "=" + URLEncoder.encode("110", "UTF-8"));
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

            String sido_sgg_nm = getTagValue("sido_sgg_nm", elem);
            String spot_nm = getTagValue("spot_nm", elem);
            String occrrnc_cnt = getTagValue("occrrnc_cnt", elem);
            String lo_crd = getTagValue("lo_crd", elem);
            String la_crd = getTagValue("la_crd", elem);

            Section_SeniorVO vo = new Section_SeniorVO();

            vo.setSido_sgg_nm(sido_sgg_nm);
            vo.setSpot_nm(spot_nm);
            vo.setOccrrnc_cnt(Integer.parseInt(occrrnc_cnt));
            vo.setLo_crd(Double.parseDouble(lo_crd));
            vo.setLa_crd(Double.parseDouble(la_crd));

            service.insertSenior(vo);

        }
        return resultMap;
    }
    @GetMapping("/api/section/seniorAccident")
    public Map<String, Object> getSeniorAccident(
        @RequestParam String region
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        //"대구광역시" => ['대','구','광','역','시']
        char[] c = region.toCharArray();
        region = "%"+c[0]+"%"+c[1]+"%"+c[2]+"%"+c[3]+"%"+c[4]+"%";

        List<Section_SeniorVO> seniorList = service.selectSeniorRegionBunch(region);
        resultMap.put("seniorList", seniorList);

        resultMap.put("status", true);
        return resultMap;
    }
    
    @GetMapping("/api/section_senior/regions")
    public List<String> getSeniorSectionRegions() {
        return service.selectSeniorRegionName();
    }
    
    @GetMapping("/api/section_senior/sigungu")
    public List<String> getSeniorSectionSigungu(@RequestParam @Nullable String spot) {
        if(spot == null) spot = "%%";
        else spot = spot+"%";

        return service.getSeniorSigunguNames(spot);
    }

    @GetMapping("/api/section_senior/seniorAccidentAdress")
    public Map<String, Object> getBicycleAccidentAddress(@RequestParam @Nullable String selectbox) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
       
        if(selectbox == null) selectbox = "%%";
        else         selectbox = selectbox+"%";
    //    char[] c = selectbox.toCharArray();
    //    selectbox = "%"+c[0]+"%"+c[1]+"%"+c[2]+"%"+c[3]+"%"+c[4]+"%";

       List<Section_SeniorVO> seniorList2 = service.selectAddressSenior(selectbox);
       resultMap.put("seniorList2", seniorList2);

       resultMap.put("status", true);
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


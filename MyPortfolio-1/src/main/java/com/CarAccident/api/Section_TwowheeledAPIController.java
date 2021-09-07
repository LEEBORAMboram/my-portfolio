package com.CarAccident.api;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.CarAccident.service.Section_TwowheeledService;
import com.CarAccident.vo.Section_TwowheeledVO;

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
public class Section_TwowheeledAPIController {
    @Autowired
    Section_TwowheeledService service;

    @GetMapping("/api/section_twowheeled")
    public Map<String, Object> getSectionTwoWheeled() throws Exception {
        Map<String, Object> resultMap =  new LinkedHashMap<String, Object>();

        StringBuilder urlBuilder = new StringBuilder("http://taas.koroad.or.kr/data/rest/frequentzone/motorcycle");
        urlBuilder.append("?" + URLEncoder.encode("authKey", "UTF-8") + "=yVPZkSBprOQY0PsDZncIRgUyRnlXDAGWktABT%2F5XLThevfGIVCzr7zjWwbWSbkwM");
        urlBuilder.append("&" + URLEncoder.encode("searchYearCd", "UTF-8") + "=" + URLEncoder.encode("2020058", "UTF-8"));
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

            System.out.println("==========================================================");
        
            Section_TwowheeledVO vo = new Section_TwowheeledVO();

            vo.setSido_sgg_nm(sido_sgg_nm);
            vo.setSpot_nm(spot_nm);
            vo.setOccrrnc_cnt(Integer.parseInt(occrrnc_cnt));
            vo.setLo_crd(Double.parseDouble(lo_crd));
            vo.setLa_crd(Double.parseDouble(la_crd));

            service.insertSectionTwowheeled(vo);

    }
    return resultMap;
}
    @GetMapping("/api/section/TwowheeledAccident")
    public Map<String, Object> getTwowheeledAccident(
        @RequestParam String region
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        //"대구광역시" => ['대','구','광','역','시']
        char[] c = region.toCharArray();
        region = "%"+c[0]+"%"+c[1]+"%"+c[2]+"%"+c[3]+"%"+c[4]+"%";

        List<Section_TwowheeledVO> twowheeledList = service.selectSectionTwowheeled(region);
        resultMap.put("twowheeledList", twowheeledList);

        resultMap.put("status", true);
        return resultMap;
    }
        @GetMapping("/api/section_twowheeled/regions")
        public List<String> getSectionTwowheeledRegions() {
            return service.selectTwowheeledName();
    
        }
        
        @GetMapping("/api/section_twowheeled/sigungu")
        public List<String> getTwowheeledSigungu(@RequestParam @Nullable String spot) {
            if(spot == null) spot = "%%";
            else spot = spot+"%";

            return service.selectTwowheeledSigunguName(spot);
        }

        @GetMapping("/api/section_twowheeled/address")
        public Map<String, Object> getTwowheeledListAccidentAddress(@RequestParam @Nullable String selectbox) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
    
            if(selectbox == null) selectbox = "%%";
            else selectbox = selectbox+"%";

            List<Section_TwowheeledVO> twowheeledList2 = service.selectTwowheeledAddress(selectbox);
            resultMap.put("twowheeledList2", twowheeledList2);

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

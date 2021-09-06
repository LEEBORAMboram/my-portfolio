package com.CarAccident.api;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.CarAccident.service.Section_RegionalService;
import com.CarAccident.vo.Section_RegionalVO;

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
public class Section_RegionalAPIController {
    @Autowired
    Section_RegionalService service;

    @GetMapping("/api/section_regional")
    public Map<String, Object> getSectionRegional_() throws Exception {
        Map<String, Object> resultMap =  new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("http://taas.koroad.or.kr/data/rest/frequentzone/lg");
        urlBuilder.append("?" + URLEncoder.encode("authKey", "UTF-8") + "=Vc%2FW%2B6XXUc1WksJZm9ZOJP%2BzhmEx3ePhwdrN02mcnbofxqGjoRKmOPL774pBUuu5");
        urlBuilder.append("&" + URLEncoder.encode("searchYearCd", "UTF-8") + "=" + URLEncoder.encode("2021056", "UTF-8"));
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

            String spot_nm = getTagValue("spot_nm", elem);
            String sido_sgg_nm = getTagValue("sido_sgg_nm", elem);
            String occrrnc_cnt = getTagValue("occrrnc_cnt", elem);
            String caslt_cnt = getTagValue("caslt_cnt", elem);
            String dth_dnv_cnt = getTagValue("dth_dnv_cnt", elem);
            String se_dnv_cnt = getTagValue("se_dnv_cnt", elem);
            String sl_dnv_cnt = getTagValue("sl_dnv_cnt", elem);
            String wnd_dnv_cnt = getTagValue("wnd_dnv_cnt", elem);
            String lo_crd = getTagValue("lo_crd", elem);
            String la_crd = getTagValue("la_crd", elem);
            
            System.out.println("==============================================");
            Section_RegionalVO vo = new Section_RegionalVO();
            vo.setSpot_nm(spot_nm);
            vo.setSido_sgg_nm(sido_sgg_nm);
            vo.setOccrrnc_cnt(Integer.parseInt(occrrnc_cnt));
            vo.setCaslt_cnt(Integer.parseInt(caslt_cnt));
            vo.setDth_dnv_cnt(Integer.parseInt(dth_dnv_cnt));
            vo.setSe_dnv_cnt(Integer.parseInt(se_dnv_cnt));
            vo.setSl_dnv_cnt(Integer.parseInt(sl_dnv_cnt));
            vo.setWnd_dnv_cnt(Integer.parseInt(wnd_dnv_cnt));
            vo.setLo_crd(Double.parseDouble(lo_crd));
            vo.setLa_crd(Double.parseDouble(la_crd));

            service.insertSectionRegional(vo);

        }
        return resultMap;
    }
    @GetMapping("/api/section/RegionalAccident")
    public Map<String, Object> getRegionalAccident(
        @RequestParam String region
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        //"대구광역시" => ['대','구','광','역','시']
        char[] c = region.toCharArray();
        region = "%"+c[0]+"%"+c[1]+"%"+c[2]+"%"+c[3]+"%"+c[4]+"%";

        List<Section_RegionalVO> regionalList = service.selectSectionRegional(region);
        resultMap.put("regionalList", regionalList);

        resultMap.put("status", true);
        return resultMap;
    }
    @GetMapping("/api/section_regional/regions")
    public List<String> getSectionRegionalRegions() {
        return service.selectRegionalName();
    }
    @GetMapping("/api/section_regional/sigungu")
    public List<String> getRegionalSigungu(@RequestParam @Nullable String spot) {
        if(spot == null) spot = "%%";
        else spot = spot+"%";

        return service.selectRegionalSigunguName(spot);
    }
    @GetMapping("/api/section_jregional/address")
    public Map<String, Object> getRegionalAccidentAddress(@RequestParam @Nullable String selectbox) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
       
        if(selectbox == null) selectbox = "%%";
        else selectbox = selectbox+"%";

        List<Section_RegionalVO> regionalList2 = service.selectRegionalAddress(selectbox);
        resultMap.put("regionalList2", regionalList2);

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

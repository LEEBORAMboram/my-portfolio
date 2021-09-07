package com.CarAccident.api;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.CarAccident.service.Section_JaywalkingService;
import com.CarAccident.vo.Section_JaywalkingVO;

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
public class Section_JaywalkingAPIController {
    @Autowired
    Section_JaywalkingService service;

    @GetMapping("/api/section_jaywalking")
    public Map<String, Object> getSection() throws Exception {
        Map<String, Object> resultMap =  new LinkedHashMap<String, Object>();
        StringBuilder urlBuilder = new StringBuilder("http://taas.koroad.or.kr/data/rest/frequentzone/pdestrians/jaywalking");
        urlBuilder.append("?" + URLEncoder.encode("authKey", "UTF-8") + "=FrlHbd9E8BdQ4VfAI96iUHBEspiKsespkol5Kzhr2sHHCMzUQ%2FPZ1AUeRClXLOzC");
        urlBuilder.append("&" + URLEncoder.encode("searchYearCd", "UTF-8") + "=" + URLEncoder.encode("2020055", "UTF-8"));
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

            String sido_sgg_nm = getTagValue("sido_sgg_nm",elem);
            String spot_nm = getTagValue("spot_nm",elem);
            String occrrnc_cnt = getTagValue("occrrnc_cnt",elem);
            String caslt_cnt = getTagValue("caslt_cnt",elem);
            String dth_dnv_cnt = getTagValue("dth_dnv_cnt",elem);
            String se_dnv_cnt = getTagValue("se_dnv_cnt",elem);
            String sl_dnv_cnt = getTagValue("sl_dnv_cnt",elem);
            String wnd_dnv_cnt = getTagValue("wnd_dnv_cnt",elem);
            String lo_crd = getTagValue("lo_crd",elem);
            String la_crd = getTagValue("la_crd",elem);

            System.out.println("==============================================");
            Section_JaywalkingVO vo = new Section_JaywalkingVO();

            vo.setSido_sgg_nm(sido_sgg_nm);
            vo.setSpot_nm(spot_nm);
            vo.setOccrrnc_cnt(Integer.parseInt(occrrnc_cnt));
            vo.setCaslt_cnt(Integer.parseInt(caslt_cnt));
            vo.setDth_dnv_cnt(Integer.parseInt(dth_dnv_cnt));
            vo.setSe_dnv_cnt(Integer.parseInt(se_dnv_cnt));
            vo.setSl_dnv_cnt(Integer.parseInt(sl_dnv_cnt));
            vo.setWnd_dnv_cnt(Integer.parseInt(wnd_dnv_cnt));
            vo.setLo_crd(Double.parseDouble(lo_crd));
            vo.setLa_crd(Double.parseDouble(la_crd));

            service.insertSectionJaywalking(vo);
        }    
        return resultMap;
    }
    
    @GetMapping("/api/section_jaywalking/regions")
    public List<String> getSectionJaywalkingRegions() {
        return service.selectRegionName();
    }
    @GetMapping("/api/section_jaywalking/sigungu")
    public List<String> getJaywalkingSigungu(@RequestParam @Nullable String spot) {
        if(spot == null) spot = "%%";
        else spot = spot+"%";

        return service.getSigunguNames(spot);
    }
    @GetMapping("/api/section_jaywalking/address")
    public Map<String, Object> getJaywalkingAccidentAddress(@RequestParam @Nullable String selectbox) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
       
        if(selectbox == null) selectbox = "%%";
        else selectbox = selectbox+"%";

        List<Section_JaywalkingVO> jaywalkingList = service.selectAddressJaywalking(selectbox);
        resultMap.put("jaywalkingList", jaywalkingList);

       resultMap.put("status", true);
       return resultMap;
    }
    @GetMapping("/api/section/JaywalkingAccident")
    public Map<String, Object> getJaywalkingAccident(
        @RequestParam String region
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        //"대구광역시" => ['대','구','광','역','시']
        char[] c = region.toCharArray();
        region = "%"+c[0]+"%"+c[1]+"%"+c[2]+"%"+c[3]+"%"+c[4]+"%";

        List<Section_JaywalkingVO> jawalkingList2 = service.selectJaywalkingRegionBunch(region);
        resultMap.put("jawalkingList2", jawalkingList2);

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
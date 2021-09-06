package com.CarAccident.api;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.CarAccident.service.SectionService;
import com.CarAccident.vo.Section_bicycleVO;

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
public class Section_BicycleAPIController {
    @Autowired
    SectionService service4;
   
    @GetMapping("/api/section_bicycle")
    public Map<String, Object> getSection() throws Exception {
        Map<String, Object> resultMap =  new LinkedHashMap<String, Object>();
        
        StringBuilder urlBuilder = new StringBuilder("http://taas.koroad.or.kr/data/rest/frequentzone/bicycle");
        urlBuilder.append("?" + URLEncoder.encode("authKey", "UTF-8") + "=1rJzSLJd0Y1jpqRqlHPO4y6votMyOXnDKdSXibn7h2CQqRDXj%2By7D%2FiNZbZjHDHD");
        urlBuilder.append("&" + URLEncoder.encode("searchYearCd", "UTF-8") + "=" + URLEncoder.encode("2020037", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("siDo", "UTF-8") + "=" + URLEncoder.encode("28", "UTF-8"));
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

            String afos_fid = getTagValue("afos_fid", elem);
            String afos_id = getTagValue("afos_id", elem);
            //String bjd_cd = getTagValue("bjd_cd", elem);
            String spot_cd = getTagValue("spot_cd", elem);
            String sido_sgg_nm = getTagValue("sido_sgg_nm", elem);
            String spot_nm = getTagValue("spot_nm", elem);
            String occrrnc_cnt = getTagValue("occrrnc_cnt", elem);
            String caslt_cnt = getTagValue("caslt_cnt", elem);
            String dth_dnv_cnt = getTagValue("dth_dnv_cnt", elem);
            String se_dnv_cnt = getTagValue("se_dnv_cnt", elem);
            String sl_dnv_cnt = getTagValue("sl_dnv_cnt", elem);
            String wnd_dnv_cnt = getTagValue("wnd_dnv_cnt", elem);
            // String geom_json = getTagValue("geom_json", elem);
            String lo_crd = getTagValue("lo_crd", elem);
            String la_crd = getTagValue("la_crd", elem);
    
            System.out.println("=====================================");

            Section_bicycleVO vo = new Section_bicycleVO();

            vo.setAfos_fid(Integer.parseInt(afos_fid));
            vo.setAfos_id(Integer.parseInt(afos_id));
            //vo.setBjd_cd(Integer.parseInt(bjd_cd));
            vo.setSpot_cd(Integer.parseInt(spot_cd));
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

            service4.insertBicycleInfo(vo);
        }
        return resultMap;
    }
    @GetMapping("/api/section/bicycleAccident")
    public Map<String, Object> getBicycleAccident(
        @RequestParam String region
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        //"대구광역시" => ['대','구','광','역','시']
        char[] c = region.toCharArray();
        region = "%"+c[0]+"%"+c[1]+"%"+c[2]+"%"+c[3]+"%"+c[4]+"%";

        List<Section_bicycleVO> bicycleList = service4.selectRegionBunch(region);
        resultMap.put("bicycleList", bicycleList);

        resultMap.put("status", true);
        return resultMap;
    }
    
    @GetMapping("/api/section/regions")
    public List<String> getSectionRegions() {
        return service4.getRegionNames();
    }
    
    @GetMapping("/api/section/sigungu")
    public List<String> getSectionSigungu(@RequestParam @Nullable String spot) {
        if(spot == null) spot = "%%";
        else spot = spot+"%";

        return service4.selectSigunguName(spot);
    }

    @GetMapping("/api/section/bicycleAccidentAdress")
    public Map<String, Object> getBicycleAccidentAddress(@RequestParam @Nullable String selectbox) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
       
        if(selectbox == null) selectbox = "%%";
        else         selectbox = selectbox+"%";
    //    char[] c = selectbox.toCharArray();
    //    selectbox = "%"+c[0]+"%"+c[1]+"%"+c[2]+"%"+c[3]+"%"+c[4]+"%";

       List<Section_bicycleVO> bicycleList2 = service4.selectBox(selectbox);
       resultMap.put("bicycleList2", bicycleList2);

       resultMap.put("status", true);
       return resultMap;
    }
    // @GetMapping("/api/section/selectbox")
    // public List<String> getSectionSelectbox(@RequestParam @Nullable String selectbox) {
    //     if(selectbox == null) selectbox = "%%";
    //     else selectbox = "%"+selectbox+"%";

    //     return service4.selectBox2(selectbox);
    // }
    public static String getTagValue(String tag, Element elem) {
        NodeList nlList = elem.getElementsByTagName(tag).item(0).getChildNodes();
        if(nlList == null) return null;
        Node node = (Node) nlList.item(0);
        if(node == null) return null;
        return node.getNodeValue();
    }
}

package com.CarAccident.vo;

import lombok.Data;

@Data
public class Section_JaywalkingVO {
    private Integer seq;
    private String sido_sgg_nm;
    private String spot_nm;
    private Integer occrrnc_cnt;
    private Integer caslt_cnt;
    private Integer dth_dnv_cnt;
    private Integer se_dnv_cnt;
    private Integer sl_dnv_cnt;
    private Integer wnd_dnv_cnt;
    private Double lo_crd;
    private Double la_crd;
}

package com.CarAccident.vo;

import lombok.Data;
 
@Data
public class Section_bicycleVO {
    private Integer seq;
    private Integer afos_fid;
    private Integer afos_id;
    //private Integer bjd_cd;
    private Integer spot_cd;
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

    private String region;
    private String selectbox;
}

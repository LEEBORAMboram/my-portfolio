package com.CarAccident.vo;

import java.util.Date;

import lombok.Data;

@Data
public class RealInfoVO {
    private Integer seq;
    private String addressJibun;
    private Double locationDataX;
    private Double locationDataY;
    private String incidentTitle;
    private Date startDate;
    private String lane;

    private String keyword;
    private String type;
    private String search;

    private String diff;
}

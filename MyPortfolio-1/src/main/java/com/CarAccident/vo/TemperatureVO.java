package com.CarAccident.vo;

import java.util.Date;

import lombok.Data;

@Data
public class TemperatureVO {
    private Integer seq;
    private Double rainfall;
    private Double wind;
    private Double temp;
    private Date tm;

    private String tm_month;

    // private String rf;
    // private String w;
    // private String t;
}
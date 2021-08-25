package com.CarAccident.vo;

import java.util.Date;

import lombok.Data;

@Data
public class TemperatureVO {
    private Integer seq;
    private String week;
    private Date dayDt;
    private Float temperature;
}

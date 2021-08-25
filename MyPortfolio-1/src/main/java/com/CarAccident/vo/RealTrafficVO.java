package com.CarAccident.vo;

import java.util.Date;

import lombok.Data;

@Data
public class RealTrafficVO {
    private Integer seq;
    private Date start_hour;
    private String routeName;
    private String traffic_volume;
    private String shareRatio;
    private String conzoneName;
    private Date stdDate;
    private String speed;
    private String timeAvg;
}

package com.CarAccident.vo;

import java.util.Date;

import lombok.Data;

@Data
public class CarSectionVO {
    private Integer seq;
    private String roadtype;
    private String event_type;
    private String eventDetailType;
    private Date startDt;
    private String roadname;
    private String roadDrcType;
    private String lanesBlockType;
    private Date endDt;
}

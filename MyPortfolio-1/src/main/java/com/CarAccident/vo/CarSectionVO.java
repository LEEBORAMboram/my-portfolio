package com.CarAccident.vo;



import java.util.Date;

import lombok.Data;

@Data
public class CarSectionVO {
    private Integer seq;
    private String road_type;
    private String eventType;
    private String eventDetailType;
    private Date startDate;
    private String roadName;
    private String roadDrcType;
    private String lanesBlockType;
    private Date endDt;
}

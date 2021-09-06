package com.CarAccident.vo;

import lombok.Data;

@Data
public class RoadriskindexVO {
    private Integer seq;
    private Integer road_index;
    private String line_string;
    private Double anals_value;
    private Integer anals_grd;
    private String highway;
    private String highway_section;
}

package com.CarAccident.vo;

import lombok.Data;

@Data
public class CarAccidentVO {
    private Integer seq;
    private Integer slightly;
    private Integer injured;
    private Integer death;
    private String gender;
    private String age;
    private Integer seriously;

    private String strDeath;
    private String strInjured;
}

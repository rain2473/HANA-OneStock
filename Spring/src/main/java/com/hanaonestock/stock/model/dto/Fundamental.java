package com.hanaonestock.stock.model.dto;

import lombok.Data;

import java.util.List;
@Data
public class Fundamental {

    private String isin;
    private String date;
    private String per;
    private String eps;
    private String bps;
    private String pbr;
    private String div;
    private String dps;

}

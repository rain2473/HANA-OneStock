package com.hanaonestock.transaction.model.dto;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

public class DailyPerformance {
    private String dateBuy;
    private String dailyPerformance;
    private String goal;

    // Default constructor
    public DailyPerformance() {
    }
}

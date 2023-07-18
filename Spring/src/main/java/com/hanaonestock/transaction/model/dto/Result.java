package com.hanaonestock.transaction.model.dto;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Result {
    @Id
    private String id; // 사용자 아이디
    private String name;
    private String isin;
    private int buy;
    private int sell; // nullable
    private int volume;
    private String dateBuy;
    private String dateSell; // nullable

    // Default constructor
    public Result() {
    }
}

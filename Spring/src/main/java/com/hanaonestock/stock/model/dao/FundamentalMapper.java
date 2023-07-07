package com.hanaonestock.stock.model.dao;

import com.hanaonestock.stock.model.dto.Fundamental;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FundamentalMapper {

    List<Fundamental> findAll();

    Fundamental findByIsinAndDate(String isin, String Date);

    void insertData(Fundamental fundamental);

}

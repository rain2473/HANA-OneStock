package com.hanaonestock.stock.model.dao;

import com.hanaonestock.stock.model.dto.Fundamental;
import com.hanaonestock.stock.model.dto.Ohlcv;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OhlcvMapper {

    List<Ohlcv> findAll();

    Ohlcv findByIsinAndDate(String isin, String Date);

    void insertData(Ohlcv ohlcv);

}

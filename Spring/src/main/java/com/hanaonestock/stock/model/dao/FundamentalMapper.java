package com.hanaonestock.stock.model.dao;

import com.hanaonestock.stock.model.dto.Fundamental;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface FundamentalMapper {

    List<Fundamental> findAll();

}

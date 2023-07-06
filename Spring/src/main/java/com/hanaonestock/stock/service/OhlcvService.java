package com.hanaonestock.stock.service;

import com.hanaonestock.stock.model.dto.Ohlcv;

import java.util.List;

public interface OhlcvService {

    public List<Ohlcv> findAll();

}

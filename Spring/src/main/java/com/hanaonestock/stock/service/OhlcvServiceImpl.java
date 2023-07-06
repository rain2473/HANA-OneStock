package com.hanaonestock.stock.service;

import com.hanaonestock.stock.model.dao.OhlcvMapper;
import com.hanaonestock.stock.model.dto.Ohlcv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OhlcvServiceImpl implements OhlcvService{

    private OhlcvMapper ohlcvMapper;

    @Autowired
    public OhlcvServiceImpl(OhlcvMapper ohlcvMapper){
        this.ohlcvMapper = ohlcvMapper;
    }

    @Override
    public List<Ohlcv> findAll(){
        return ohlcvMapper.findAll();
    }

}

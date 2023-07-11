package com.hanaonestock.stock.service;

import com.hanaonestock.stock.model.dao.OhlcvMapper;
import com.hanaonestock.stock.model.dto.Ohlcv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OhlcvServiceImpl implements OhlcvService{

    private OhlcvMapper ohlcvMapper;
    private LocalDate currentDate = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private String now = currentDate.format(formatter);


    @Autowired
    public OhlcvServiceImpl(OhlcvMapper ohlcvMapper){
        this.ohlcvMapper = ohlcvMapper;
    }

    @Override
    public List<Ohlcv> findAll(){
        return ohlcvMapper.findAll();
    }

    @Override
    public List<Ohlcv> findRisingTop5ByDate() {
        return ohlcvMapper.findRisingTop5ByDate(now);
    }

    @Override
    public List<Ohlcv> findFallingTop5ByDate() {
        return ohlcvMapper.findFallingTop5ByDate(now);
    }

    @Override
    public List<Ohlcv> findVolumeTop5ByDate() {
        return ohlcvMapper.findVolumeTop5ByDate(now);
    }

    @Override
    public List<Ohlcv> findAmountTop5ByDate() {
        return ohlcvMapper.findAmountTop5ByDate(now);
    }

}

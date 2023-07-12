package com.hanaonestock.transaction.service;

import com.hanaonestock.stock.model.dto.Ohlcv;
import com.hanaonestock.transaction.model.dto.BuyDto;
import com.hanaonestock.transaction.model.dto.SellDto;

import java.util.List;
import java.util.Map;

public interface TransactionService {
    // 매수
    int buy(BuyDto buyDto);

    // 매도
    int sell(SellDto sellDto);

    // 종목 검색
    List<Ohlcv> search(String stockName);

    // 보유 해당 주식 수
    int sumHasVolume(String id, String isin);

}

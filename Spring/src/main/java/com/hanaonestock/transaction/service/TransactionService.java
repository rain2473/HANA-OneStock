package com.hanaonestock.transaction.service;

import com.hanaonestock.stock.model.dto.Ohlcv;
import com.hanaonestock.transaction.model.dto.BuyDto;
import com.hanaonestock.transaction.model.dto.SellDto;
import com.hanaonestock.transaction.model.dto.Result;
import java.util.List;

public interface TransactionService {
    // 매수
    int buy(BuyDto buyDto);

    // 매도
    int sell(SellDto sellDto);

    // 종목 검색
    List<Ohlcv> search(String stockName);

    // 보유 해당 주식 수
    int sumHasVolume(String id, String isin);

    // 회원의 거래내역 조회
    List<Result> transactionsByMember(String id);

}

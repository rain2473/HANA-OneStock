package com.hanaonestock.transaction.service;

import com.hanaonestock.stock.model.dao.OhlcvMapper;
import com.hanaonestock.stock.model.dao.StockMapper;
import com.hanaonestock.stock.model.dto.Ohlcv;
import com.hanaonestock.stock.model.dto.Stock;
import com.hanaonestock.transaction.model.dao.TransactionMapper;
import com.hanaonestock.transaction.model.dto.BuyDto;
import com.hanaonestock.transaction.model.dto.SellDto;
import com.hanaonestock.transaction.model.dto.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionMapper transactionMapper;
    private final OhlcvMapper ohlcvMapper;
    private final StockMapper stockMapper;

    private LocalDate currentDate = LocalDate.now();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private String now = currentDate.format(formatter);
    @Autowired
    public TransactionServiceImpl(TransactionMapper transactionMapper, OhlcvMapper ohlcvMapper, StockMapper stockMapper) {
        this.transactionMapper = transactionMapper;
        this.ohlcvMapper = ohlcvMapper;
        this.stockMapper = stockMapper;
    }

    @Override
    public int buy(BuyDto buyDto) {
        int state = 0;
        Transaction buyTransaction = new Transaction();
        buyTransaction.setId(buyDto.getId());
        buyTransaction.setIsin(buyDto.getIsin());
        buyTransaction.setBuy(buyDto.getPrice());
        buyTransaction.setVolume(buyDto.getVolume());
        buyTransaction.setDateBuy(now);
        buyTransaction.setDuration("S");

        try {
            state = transactionMapper.insertBuyTransaction(buyTransaction);
        } catch (Exception e){
            e.printStackTrace();
        }

        return state;
    }

    @Override
    public int sell(SellDto sellDto) {
        int state = 0;
        Transaction sellTransaction = new Transaction();
        sellTransaction.setId(sellDto.getId());
        sellTransaction.setIsin(sellDto.getIsin());
        sellTransaction.setSell(sellDto.getPrice());
        sellTransaction.setVolume(sellDto.getVolume());
        sellTransaction.setDateSell(now);
        sellTransaction.setDuration("S");

        try {
            state = transactionMapper.updateSellTransaction(sellTransaction);
        } catch (Exception e){
            e.printStackTrace();
        }

        return state;
    }

    @Override
    public Ohlcv search(String input) {
        Ohlcv ohlcv = null;
        Stock stock;
        Optional<Stock> stockOptional;

        try {
            stockOptional = stockMapper.findByName(input);

            // 이름 검색 결과 확인
            if (stockOptional.isPresent()) {
                stock = stockOptional.get();
                ohlcv = ohlcvMapper.findByIsin(stock.getIsin()).get();
            } else {
                // 이름 검색 결과 없음
                stockOptional = stockMapper.findByIsin(input);

                // isin 검색 결과 확인
                if (stockOptional.isPresent()) {
                    stock = stockOptional.get();
                    ohlcv = ohlcvMapper.findByIsin(stock.getIsin()).get();
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return ohlcv;
    }
}

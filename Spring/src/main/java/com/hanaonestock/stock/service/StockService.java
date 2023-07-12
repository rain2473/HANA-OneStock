package com.hanaonestock.stock.service;


import com.hanaonestock.stock.model.dto.RecommendedStock;
import com.hanaonestock.stock.model.dto.Stock;

import java.util.List;

public interface StockService {

    // 종목 검색
    Stock search(String input);

    // 종목 검색 중
    List<Stock> searching(String input);

    List<RecommendedStock> recommendedStock();

}

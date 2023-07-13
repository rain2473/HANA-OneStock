package com.hanaonestock.stock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanaonestock.stock.model.dto.Ohlcv;
import com.hanaonestock.stock.model.dto.RecommendedStock;
import com.hanaonestock.stock.model.dto.Stock;
import com.hanaonestock.stock.service.OhlcvService;
import com.hanaonestock.stock.service.StockService;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

@Controller
public class StockController {

    private final StockService stockService;
    private final OhlcvService ohlcvService;

    @Autowired
    StockController(StockService stockService, OhlcvService ohlcvService) {
        this.stockService = stockService;
        this.ohlcvService = ohlcvService;
    }

    @ResponseBody
    @GetMapping(value = "/stock-searching")
    public ResponseEntity<List<Stock>> stockSearching(@RequestParam("input") String input) {
        List<Stock> stockList = stockService.searching(input);
        System.out.println(input);
        if (stockList != null && !stockList.isEmpty()) {
            System.out.println(ResponseEntity.ok(stockList));
            return ResponseEntity.ok(stockList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping(value = "/stock-search")
    public ResponseEntity<Stock> stockSearch(@RequestParam("input") String input) {
        Stock stock = stockService.search(input);
        if (stock != null) {
            return ResponseEntity.ok(stock);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/main")
    public ModelAndView main(HttpSession session,@RequestParam("goal") String goal) {
        ModelAndView mav = new ModelAndView();
        HashMap<String, String> stockData = new HashMap<>();
        stockData.put("id",(String)session.getAttribute("id"));
        stockData.put("goal",goal);
        stockService.updateGoalOfInvestInfoById(stockData);
        List<RecommendedStock> stockList = stockService.recommendedStock();
        session.setAttribute("stockList", stockList);
        mav.addObject("stockList", stockList);
        mav.setViewName("main");
        return mav;
    }

    @ResponseBody
    @GetMapping(value = "/special-stock/rising-top5")
    public ResponseEntity<List<Map<String, String>>> findRisingTop5() {
        List<Map<String, String>> resultList = new ArrayList<>();
        List<Ohlcv> ohlcvList = ohlcvService.findRisingTop5ByDate();
        for (Ohlcv ohlcv : ohlcvList) {
            Map<String, String> stockMap = new HashMap<>();
            stockMap.put("isin", ohlcv.getIsin());
            stockMap.put("name", ohlcv.getName());
            resultList.add(stockMap);
        }
        if (!resultList.isEmpty()) {
            return ResponseEntity.ok(resultList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping(value = "/special-stock/falling-top5")
    public ResponseEntity<List<Map<String, String>>> findFallingTop5() {
        List<Map<String, String>> resultList = new ArrayList<>();
        List<Ohlcv> ohlcvList = ohlcvService.findFallingTop5ByDate();
        List<String> stockNames = null;
        for (Ohlcv ohlcv : ohlcvList) {
            Map<String, String> stockMap = new HashMap<>();
            stockMap.put("isin", ohlcv.getIsin());
            stockMap.put("name", ohlcv.getName());
            resultList.add(stockMap);
        }
        if (!resultList.isEmpty()) {
            return ResponseEntity.ok(resultList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping(value = "/special-stock/volume-top5")
    public ResponseEntity<List<Map<String, String>>> findVolumeTop5() {
        List<Map<String, String>> resultList = new ArrayList<>();
        List<Ohlcv> ohlcvList = ohlcvService.findVolumeTop5ByDate();
        for (Ohlcv ohlcv : ohlcvList) {
            Map<String, String> stockMap = new HashMap<>();
            stockMap.put("isin", ohlcv.getIsin());
            stockMap.put("name", ohlcv.getName());
            resultList.add(stockMap);
        }
        if (!resultList.isEmpty()) {
            return ResponseEntity.ok(resultList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping(value = "/special-stock/amount-top5")
    public ResponseEntity<List<Map<String, String>>> findAmountTop5() {
        List<Map<String, String>> resultList = new ArrayList<>();
        List<Ohlcv> ohlcvList = ohlcvService.findAmountTop5ByDate();
        List<String> stockNames = null;
        for (Ohlcv ohlcv : ohlcvList) {
            Map<String, String> stockMap = new HashMap<>();
            stockMap.put("isin", ohlcv.getIsin());
            stockMap.put("name", ohlcv.getName());
            resultList.add(stockMap);
        }
        if (!resultList.isEmpty()) {
            return ResponseEntity.ok(resultList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

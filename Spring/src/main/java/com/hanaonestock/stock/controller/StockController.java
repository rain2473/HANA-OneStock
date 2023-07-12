package com.hanaonestock.stock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanaonestock.stock.model.dto.Ohlcv;
import com.hanaonestock.stock.model.dto.RecommendedStock;
import com.hanaonestock.stock.model.dto.Stock;
import com.hanaonestock.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StockController {

    private final StockService stockService;

    @Autowired
    StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @ResponseBody
    @GetMapping(value = "/stock-searching")
    public ResponseEntity<List<Stock>> stockSearching(@RequestParam("input") String input) {
        List<Stock> stockList = stockService.searching(input);
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
    public ModelAndView main() {
        ModelAndView mav = new ModelAndView();

        List<RecommendedStock> stockList = stockService.recommendedStock();
        for(RecommendedStock rs : stockList) {
            System.out.println(rs.toString());
        }
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json;
//        try {
//            json = objectMapper.writeValueAsString(stockList);
//        } catch (JsonProcessingException e) {
//            return new ResponseEntity<>("Error processing JSON", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        mav.addObject("stockList", stockList);
        //model.addAttribute("stockList", stockList);
       // return new ResponseEntity<>(json, HttpStatus.OK);

        mav.setViewName("main");
        return mav;
    }

}

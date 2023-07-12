package com.hanaonestock.transaction.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanaonestock.member.model.dto.InvestInfo;
import com.hanaonestock.member.service.MemberService;
import com.hanaonestock.stock.model.dto.Ohlcv;
import com.hanaonestock.stock.model.dto.Stock;
import com.hanaonestock.transaction.model.dto.BuyDto;
import com.hanaonestock.transaction.model.dto.SellDto;
import com.hanaonestock.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TransactionController {

    private final TransactionService transactionService;
    private final MemberService memberService;
    @Autowired
    public TransactionController(TransactionService transactionService, MemberService memberService) {
        this.transactionService = transactionService;
        this.memberService = memberService;
    }

    @RequestMapping("/trading")
    public ModelAndView trading() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("trading");
        return mav;
    }

    @ResponseBody
    @GetMapping(value = "/get-chart")
    public ResponseEntity<String> getStockChartData(@RequestParam("input") String input) {
        List<Ohlcv> ohlcvList = transactionService.search(input);
        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(ohlcvList);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>("Error processing JSON", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/get-user-cash")
    public ResponseEntity<Integer> getUserCash(@RequestParam("id") String id) {
        Integer cash = memberService.findUserCash(id);
        System.out.println("id = " + id);
        System.out.println("캐쉬: " + memberService.findUserCash(id));
        if (cash == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(cash);
    }

    @PostMapping("/buy-transaction")
    public ResponseEntity<?> processBuyTransaction(@RequestBody BuyDto buyDto) {
        int state = transactionService.buy(buyDto);
        if (state != 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // 모든 것이 정상적으로 처리되면, HTTP 상태 코드 200(OK)을 반환합니다.
        return ResponseEntity.ok().build();
    }

    @PostMapping("/sell-transaction")
    public ResponseEntity<?> processSellTransaction(@RequestBody SellDto sellDto) {
        System.out.println(sellDto.toString());
        int state = transactionService.sell(sellDto);
        if (state < 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        // 모든 것이 정상적으로 처리되면, HTTP 상태 코드 200(OK)을 반환합니다.
        return ResponseEntity.ok().build();
    }
}

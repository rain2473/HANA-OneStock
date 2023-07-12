package com.hanaonestock.stock.controller;

import com.hanaonestock.stock.model.dto.Ohlcv;
import com.hanaonestock.stock.model.dto.Stock;
import com.hanaonestock.stock.service.OhlcvService;
import com.hanaonestock.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    public ModelAndView main(@RequestParam("goal") String goal) {
        try {
            String requestUrl = "http://data-dbg.krx.co.kr/svc/apis/idx/kospi_dd_trd?basDd=20200414";

            // HTTP 연결 설정
            URL url = new URL(requestUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // AUTH_KEY 추가
            connection.setRequestProperty("AUTH_KEY", "1CF0397443A047CD97A13094FB0698E116C1A36A");

            // API 응답 코드 확인
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // API 응답 읽기
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // API 응답 데이터 출력
                String jsonResponse = response.toString();
                System.out.println("API 응답:");
                System.out.println(jsonResponse);
            } else {
                System.out.println("API 연결 실패. 응답 코드: " + responseCode);
            }

            // 연결 종료
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        return mav;
    }

    @ResponseBody
    @GetMapping(value = "/special-stock/rising-top5")
    public ResponseEntity<Map<List<Ohlcv>, List<String>>> findRisingTop5() {
        Map<List<Ohlcv>, List<String>> risingTop5 = null;
        List<Ohlcv> ohlcvList = ohlcvService.findRisingTop5ByDate();
        List<String> stockNames = null;

        for (Ohlcv ohlcv: ohlcvList) {
            stockNames.add(ohlcv.getIsin());
        }

        if (risingTop5 != null) {
            return ResponseEntity.ok(risingTop5);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping(value = "/special-stock/falling-top5")
    public ResponseEntity<Map<List<Ohlcv>, List<String>>> findFallingTop5() {
        Map<List<Ohlcv>, List<String>> fallingTop5 = null;
        List<Ohlcv> ohlcvList = ohlcvService.findFallingTop5ByDate();
        List<String> stockNames = null;

        for (Ohlcv ohlcv: ohlcvList) {
            stockNames.add(ohlcv.getIsin());
        }

        if (fallingTop5 != null) {
            return ResponseEntity.ok(fallingTop5);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping(value = "/special-stock/volume-top5")
    public ResponseEntity<Map<List<Ohlcv>, List<String>>> findVolumeTop5() {
        Map<List<Ohlcv>, List<String>> volumeTop5 = null;
        List<Ohlcv> ohlcvList = ohlcvService.findVolumeTop5ByDate();
        List<String> stockNames = null;

        for (Ohlcv ohlcv: ohlcvList) {
            stockNames.add(ohlcv.getIsin());
        }

        if (volumeTop5 != null) {
            return ResponseEntity.ok(volumeTop5);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseBody
    @GetMapping(value = "/special-stock/amount-top5")
    public ResponseEntity<Map<List<Ohlcv>, List<String>>> findAmountTop5() {
        Map<List<Ohlcv>, List<String>> risingTop5 = null;
        List<Ohlcv> ohlcvList = ohlcvService.findAmountTop5ByDate();
        List<String> stockNames = null;

        for (Ohlcv ohlcv: ohlcvList) {
            stockNames.add(ohlcv.getIsin());
        }

        if (risingTop5 != null) {
            return ResponseEntity.ok(risingTop5);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

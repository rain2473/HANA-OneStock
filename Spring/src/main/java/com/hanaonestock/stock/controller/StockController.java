package com.hanaonestock.stock.controller;

import com.hanaonestock.stock.model.dto.Stock;
import com.hanaonestock.stock.service.StockService;
import org.json.JSONArray;
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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.json.JSONObject;
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
            // Define date range
            LocalDate startDate = LocalDate.of(2022, 7, 12);
            LocalDate endDate = LocalDate.of(2023, 7, 11);
            LocalDate date = startDate;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            while (!date.isAfter(endDate)) {
                // Construct the request URL for each date
                String requestUrl = "http://data-dbg.krx.co.kr/svc/apis/idx/kospi_dd_trd?basDd=" + date.format(formatter);

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

                    JSONObject responseJson = new JSONObject(jsonResponse);
                    JSONArray outBlock1 = responseJson.getJSONArray("OutBlock_1");

                    // Search for the matching index data
                    for (int i = 0; i < outBlock1.length(); i++) {
                        JSONObject index = outBlock1.getJSONObject(i);
                        String indexName = index.getString("IDX_NM");
                        String indexDate = index.getString("BAS_DD");

                        // If the index name is "코스피 100" and the date matches, extract the information
                        if (indexName.equals("코스피 200") && indexDate.equals(date.format(formatter))) {
                            String closingPrice = index.getString("CLSPRC_IDX");
                            String priceChange = index.getString("CMPPREVDD_IDX");
                            String fluctuationRate = index.getString("FLUC_RT");
                            String volume = index.getString("ACC_TRDVOL");
                            String value = index.getString("ACC_TRDVAL");
                            String marketCap = index.getString("MKTCAP");

                            break; // Exit the loop since we found the matching index data
                        }
                    }
                    System.out.println();
                } else {
                    System.out.println("API 연결 실패. 응답 코드: " + responseCode);
                }

                // Move to the next day
                date = date.plusDays(1);

                // 연결 종료
                connection.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");
        return mav;
    }


}

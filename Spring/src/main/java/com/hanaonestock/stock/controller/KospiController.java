package com.hanaonestock.stock.controller;

import com.hanaonestock.stock.model.dto.Fundamental;
import com.hanaonestock.stock.model.dto.Ohlcv;
import com.hanaonestock.stock.model.dto.Predict;
import com.hanaonestock.stock.service.KospiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class KospiController {
    @Value("${flask.server.url}")
    private String flaskServerUrl;
    private LocalDate today;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final String kospiStr = "kospi";

    private String getResquestJson(String str) {
        today = LocalDate.now();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> flaskResponse = restTemplate.getForEntity(flaskServerUrl + "/stock_info/" + str + "/" + today.format(formatter), String.class);
        return flaskResponse.getBody().replaceAll("^\"|\"$", "").replaceAll("\\\\", "").toLowerCase();
    }

    @ResponseBody
    @GetMapping(value = "/kospi")
    public ResponseEntity<String> index(Model model) {
        System.out.println("들어오나요??");
        String kospiJson = getResquestJson(kospiStr);
        System.out.println("kospiJson = "+kospiJson);
        model.addAttribute("kospi", kospiJson);
        return new ResponseEntity<>(kospiJson, HttpStatus.OK);
    }
}

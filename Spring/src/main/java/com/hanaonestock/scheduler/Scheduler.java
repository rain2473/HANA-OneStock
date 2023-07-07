package com.hanaonestock.scheduler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanaonestock.stock.model.dao.FundamentalMapper;
import com.hanaonestock.stock.model.dao.OhlcvMapper;
import com.hanaonestock.stock.model.dto.Fundamental;
import com.hanaonestock.stock.model.dto.Ohlcv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Component
public class Scheduler {

    @Autowired
    OhlcvMapper ohlcvMapper;
    @Autowired
    FundamentalMapper fundamentalMapper;

    @Value("${flask.server.url}")
    private String flaskServerUrl;
    private LocalDate today;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final ObjectMapper mapper = new ObjectMapper();
    public static final String ohlcvStr = "ohlcv";
    public static final String fundamentalStr = "fundamental";


    public String getResquestJson(String str) {
        today = LocalDate.now();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> fundamentalResponse = restTemplate.getForEntity(flaskServerUrl + "/stock_info/" + str + "/" + today.format(formatter), String.class);
        return fundamentalResponse.getBody().replaceAll("^\"|\"$", "").replaceAll("\\\\", "").toLowerCase();
    }

    public List<Fundamental> createFundamentalsFromJson(String json) {

        try {
            // Remove the starting and ending double quotes from the JSON string
            json = json.substring(1, json.length() - 1);
            List<Fundamental> fundamentals = mapper.readValue(json, new TypeReference<List<Fundamental>>() {
            });
            return fundamentals;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Ohlcv> createOhlcvsFromJson(String json) {

        try {
            // Remove the starting and ending double quotes from the JSON string
            json = json.substring(1, json.length() - 1);
            List<Ohlcv> Ohlcvs = mapper.readValue(json, new TypeReference<List<Ohlcv>>() {
            });
            return Ohlcvs;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // @Scheduled(cron = "0 0 16 * * ?")
    public void runAt4PMGet() {

        String fundamentalJson = getResquestJson(fundamentalStr);
        String ohlcvJson = getResquestJson(ohlcvStr);

        List<Fundamental> fundamentals = createFundamentalsFromJson(fundamentalJson);
        List<Ohlcv> ohlcvs = createOhlcvsFromJson(ohlcvJson);

        for (Fundamental fundamental : fundamentals) {
            System.out.println(fundamental.toString());
        }
        for (Ohlcv ohlcv : ohlcvs) {
            System.out.println(ohlcv.toString());
        }

        saveFundamental(fundamentals);
        saveOhlcv(ohlcvs);
    }

    private void saveFundamental(List<Fundamental> fundamentals) {
        for (Fundamental fundamental : fundamentals) {
            fundamentalMapper.insertData(fundamental);
        }
    }

    private void saveOhlcv(List<Ohlcv> ohlcvs) {
        for (Ohlcv ohlcv : ohlcvs) {
            ohlcvMapper.insertData(ohlcv);
        }
    }
}

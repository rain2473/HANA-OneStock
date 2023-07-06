package com.hanaonestock.scheduler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanaonestock.stock.model.dao.OhlcvMapper;
import com.hanaonestock.stock.model.dto.Ohlcv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class Scheduler {

    @Autowired
    OhlcvMapper ohlcvMapper;

    @Value("${flask.server.url}")
    private String flaskServerUrl;
    private ObjectMapper objectMapper = new ObjectMapper();

    public String getResquestString() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return flaskServerUrl + "/stock_info/" + today.format(formatter);
    }

    // @Scheduled(cron = "0 0 16 * * ?")
    public void runAt4PMGet() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(getResquestString(), String.class);
        String json = response.getBody();
        System.out.println(json);

        try {
            Map<String, List<Map<String, Object>>> jsonData = objectMapper.readValue(json, Map.class);

            List<Map<String, Object>> table1Data = jsonData.get("new_ohlcv");
            List<Map<String, Object>> table2Data = jsonData.get("new_fundamental");

            saveTableData("new_ohlcv", table1Data);
            saveTableData("new_fundamental", table2Data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveTableData(String tableName, List<Map<String, Object>> data) {

        for (Map<String, Object> row : data) {

            Ohlcv ohlcv = new Ohlcv();

            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String column = entry.getKey();
                Object value = entry.getValue();

                switch (column.toLowerCase()) {
                    case "isin":
                        ohlcv.setIsin((String) value);
                        break;
                    case "s_date":
                        // Assuming date is stored as a String in "yyyy-MM-dd" format in your Map
                        try {
                            Date date = new SimpleDateFormat("yyyyMMdd").parse((String) value);
                            ohlcv.setS_date(date);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "open":
                        ohlcv.setOpen((int) value);
                        break;
                    case "high":
                        ohlcv.setHigh((int) value);
                        break;
                    case "low":
                        ohlcv.setLow((int) value);
                        break;
                    case "close":
                        ohlcv.setClose((int) value);
                        break;
                    case "volume":
                        ohlcv.setVolume((long) value);
                        break;
                    case "amount":
                        ohlcv.setAmount((long) value);
                        break;
                    case "updown":
                        ohlcv.setUpdown((double) value);
                        break;
                    default:
                        break;
                }
            }
            ohlcvMapper.insertData(ohlcv);
        }
    }
}

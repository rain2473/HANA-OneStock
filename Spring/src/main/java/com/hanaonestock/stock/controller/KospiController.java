package com.hanaonestock.stock.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    public ResponseEntity<Resource> index() {
        String kospiJson = getResquestJson(kospiStr);
        try {
            // JSON 데이터를 파일로 저장
            Resource resource = new ClassPathResource("kospi.json");
            File file = resource.getFile();
            FileUtils.writeStringToFile(file, kospiJson, StandardCharsets.UTF_8);

            // 파일을 Resource로 변환하여 클라이언트에게 리턴
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=kospi.json")
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

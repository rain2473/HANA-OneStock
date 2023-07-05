package com.hanaonestock.demo.stock;

import com.hanaonestock.stock.model.dao.FundamentalMapper;
import com.hanaonestock.stock.model.dto.Fundamental;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {FundamentalMapper.class})
public class OracleCloudConnectionTest {

    @Autowired
    private FundamentalMapper fundamentalMapper;

    @Test
    @DisplayName("오라클 클라우드 연결 테스트")
    void connect(){

        List<Fundamental> fundamentals = fundamentalMapper.findAll();

        for(Fundamental fundamental : fundamentals) {
            System.out.println(fundamental);
        }
    }
}

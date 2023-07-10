package com.hanaonestock.demo.transaction;

import com.hanaonestock.AutoAppConfig;
import com.hanaonestock.transaction.model.dto.BuyDto;
import com.hanaonestock.transaction.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(AutoAppConfig.class)
@Transactional
public class TransactionTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    void buyTest(){
        int state = 0;
        BuyDto buyDto = new BuyDto();
        buyDto.setId("test");
        buyDto.setIsin("testIsin");
        buyDto.setPrice(10000);
        buyDto.setVolume(5);
        state = transactionService.buy(buyDto);
        assertThat(state).isEqualTo(1);
    }

    @Test
    void sellTest(){

    }

    @Test
    void searchTest(){

    }

}

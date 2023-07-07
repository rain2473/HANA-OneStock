package com.hanaonestock.demo.scheduler;

import com.hanaonestock.AutoAppConfig;
import com.hanaonestock.scheduler.Scheduler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static com.hanaonestock.scheduler.Scheduler.fundamentalStr;
import static com.hanaonestock.scheduler.Scheduler.ohlcvStr;

// @SpringBootTest
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(AutoAppConfig.class)
@Transactional
public class SchedulerTest {

    @Autowired
    private Scheduler scheduler;

    @Test
    @DisplayName("스케줄링 - restful get 요청 테스트")
    void restfulTest() {
        System.out.println(scheduler.getResquestJson(ohlcvStr));
        System.out.println(scheduler.getResquestJson(fundamentalStr));
        scheduler.runAt4PMGet();
    }
}

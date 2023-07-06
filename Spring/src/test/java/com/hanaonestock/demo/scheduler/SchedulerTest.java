package com.hanaonestock.demo.scheduler;

import com.hanaonestock.AutoAppConfig;
import com.hanaonestock.scheduler.Scheduler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

//@SpringBootTest
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(AutoAppConfig.class)
//@Transactional  // 롤백을 위한 트랜잭션 설정
public class SchedulerTest {

    @Autowired
    private Scheduler scheduler;

    @Test
    @DisplayName("스케줄링 - restful get 요청 테스트")
//    @Transactional  // 롤백을 위한 트랜잭션 설정
    void restfulTest() {
        System.out.println(scheduler.getResquestString());
        scheduler.runAt4PMGet();
    }
}

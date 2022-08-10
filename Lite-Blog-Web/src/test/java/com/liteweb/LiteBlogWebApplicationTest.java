package com.liteweb;

import com.liteweb.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class LiteBlogWebApplicationTest {
    @Test
    void test() throws ClassNotFoundException {
        log.info(DateUtils.formatNow());
    }

}

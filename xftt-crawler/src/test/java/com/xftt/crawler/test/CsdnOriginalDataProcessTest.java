package com.xftt.crawler.test;

import com.xftt.crawler.process.original.impl.CsdnOriginalDataProcess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CsdnOriginalDataProcessTest {

    @Autowired
    private CsdnOriginalDataProcess csdnOriginalDataProcess;

    @Test
    public void test1() {
        assertNotNull(csdnOriginalDataProcess);
    }
}

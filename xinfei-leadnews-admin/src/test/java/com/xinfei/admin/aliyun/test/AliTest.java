package com.xinfei.admin.aliyun.test;

import com.xinfei.common.aliyun.AliyunImageScanRequest;
import com.xinfei.common.aliyun.AliyunTextScanRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class AliTest {

    @Autowired
    private AliyunTextScanRequest aliyunTextScanRequest;

    @Autowired
    private AliyunImageScanRequest aliyunImageScanRequest;

    @Test
    public void testBeans() {
        assertNotNull(aliyunTextScanRequest);
        assertNotNull(aliyunImageScanRequest);
    }
}

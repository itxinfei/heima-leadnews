package com.xftt.images.service.impl;

import com.xftt.common.fastdfs.FastDfsClient;
import com.xftt.images.config.InitConfig;
import com.xftt.utils.common.Base64Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * CacheImageServiceImpl 单元测试：MinIO 对象名解析与缓存读写。
 */
@ExtendWith(MockitoExtension.class)
class CacheImageServiceImplTest {

    @Mock private FastDfsClient fastDfsClient;
    @Mock private StringRedisTemplate redisTemplate;
    @InjectMocks private CacheImageServiceImpl service;

    @BeforeAll
    static void setupPrefix() {
        InitConfig.PREFIX = "http://192.168.20.130:9005/xftt/";
    }

    private String resolve(String url) throws Exception {
        Method m = CacheImageServiceImpl.class.getDeclaredMethod("resolveObjectName", String.class);
        m.setAccessible(true);
        return (String) m.invoke(service, url);
    }

    @Test
    void resolve_fullUrl_stripsPrefix() throws Exception {
        assertEquals("abc.jpg", resolve("http://192.168.20.130:9005/xftt/abc.jpg"));
    }

    @Test
    void resolve_objectName_passthrough() throws Exception {
        assertEquals("abc.jpg", resolve("abc.jpg"));
    }

    @Test
    void resolve_urlWithoutPrefix_lastSegment() throws Exception {
        assertEquals("abc.jpg", resolve("http://other/x/y/abc.jpg"));
    }

    @Test
    void cache2Redis_writesCache_whenDownOk() throws Exception {
        byte[] data = "img".getBytes(StandardCharsets.UTF_8);
        when(fastDfsClient.downFile("abc.jpg")).thenReturn(data);
        ValueOperations<String, String> ops = mock(ValueOperations.class);
        when(redisTemplate.opsForValue()).thenReturn(ops);
        byte[] ret = service.cache2Redis("http://192.168.20.130:9005/xftt/abc.jpg", true);
        assertArrayEquals(data, ret);
        verify(ops).set("http://192.168.20.130:9005/xftt/abc.jpg", Base64Utils.encode(data), 86400L, TimeUnit.SECONDS);
    }

    @Test
    void cache2Redis_noCache_whenIsCacheFalse() throws Exception {
        when(fastDfsClient.downFile("abc.jpg")).thenReturn("img".getBytes(StandardCharsets.UTF_8));
        service.cache2Redis("http://192.168.20.130:9005/xftt/abc.jpg", false);
        verify(redisTemplate, never()).opsForValue();
    }

    @Test
    void cache2Redis_downThrows_returnsNull() throws Exception {
        when(fastDfsClient.downFile("abc.jpg")).thenThrow(new RuntimeException("minio down"));
        byte[] ret = service.cache2Redis("http://192.168.20.130:9005/xftt/abc.jpg", true);
        assertNull(ret);
        verify(redisTemplate, never()).opsForValue();
    }
}

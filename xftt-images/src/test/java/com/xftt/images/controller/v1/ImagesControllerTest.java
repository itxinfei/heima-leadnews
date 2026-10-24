package com.xftt.images.controller.v1;

import com.xftt.images.config.InitConfig;
import com.xftt.images.service.CacheImageService;
import com.xftt.utils.common.Base64Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * ImagesController.getImage 单元测试：命中续期 / 未命中惰性回源写缓存。
 */
@ExtendWith(MockitoExtension.class)
class ImagesControllerTest {

    @Mock private StringRedisTemplate redisTemplate;
    @Mock private CacheImageService cacheImageService;
    @InjectMocks private ImagesController controller;

    @BeforeAll
    static void setupPrefix() {
        InitConfig.PREFIX = "http://192.168.20.130:9005/xftt/";
    }

    @Test
    void getImage_hit_renewsCache() throws Exception {
        ValueOperations<String, String> ops = mock(ValueOperations.class);
        when(redisTemplate.opsForValue()).thenReturn(ops);
        when(ops.get("http://192.168.20.130:9005/xftt/abc.jpg")).thenReturn(Base64Utils.encode(new byte[] { 1, 2, 3 }));
        controller.getImage("http://192.168.20.130:9005/xftt/abc.jpg");
        verify(cacheImageService).resetCache2Redis("http://192.168.20.130:9005/xftt/abc.jpg");
        verify(cacheImageService, never()).cache2Redis(anyString(), anyBoolean());
    }

    @Test
    void getImage_miss_lazyCache() throws Exception {
        ValueOperations<String, String> ops = mock(ValueOperations.class);
        when(redisTemplate.opsForValue()).thenReturn(ops);
        when(ops.get("http://192.168.20.130:9005/xftt/abc.jpg")).thenReturn(null);
        when(cacheImageService.cache2Redis("http://192.168.20.130:9005/xftt/abc.jpg", true)).thenReturn(new byte[] { 1, 2, 3 });
        controller.getImage("http://192.168.20.130:9005/xftt/abc.jpg");
        verify(cacheImageService).cache2Redis("http://192.168.20.130:9005/xftt/abc.jpg", true);
        verify(cacheImageService, never()).resetCache2Redis(anyString());
    }
}

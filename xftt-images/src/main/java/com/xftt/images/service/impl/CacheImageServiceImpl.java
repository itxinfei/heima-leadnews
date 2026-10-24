package com.xftt.images.service.impl;

import com.xftt.common.fastdfs.FastDfsClient;
import com.xftt.images.config.InitConfig;
import com.xftt.images.service.CacheImageService;
import com.xftt.utils.common.Base64Utils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class CacheImageServiceImpl implements CacheImageService {

    @Autowired
    private FastDfsClient fastDfsClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    final long EXPIRE = 60 * 60 * 24;//24消息

    @Override
    public byte[] cache2Redis(String imgUrl, boolean isCache) {
        byte[] ret = null;
        String baseString = "";
        try {
            String objectName = resolveObjectName(imgUrl);
            ret = fastDfsClient.downFile(objectName);
            baseString = Base64Utils.encode(ret);
        } catch (Exception e) {
            log.error("缓存图片失败, imgUrl:{}", imgUrl, e);
        }
        if (isCache && ret != null) {
            redisTemplate.opsForValue().set(imgUrl, baseString, EXPIRE, TimeUnit.SECONDS);
        }
        return ret;
    }

    /**
     * 从图片 URL 解析 MinIO 对象名：完整 URL 剥 public-url(PREFIX) 前缀；否则视为已是对象名（兜底取最后一个 / 后）。
     */
    private String resolveObjectName(String imgUrl) {
        if (imgUrl == null) {
            return null;
        }
        if (imgUrl.startsWith("http")) {
            String prefix = InitConfig.PREFIX;
            if (prefix != null && imgUrl.startsWith(prefix)) {
                return imgUrl.substring(prefix.length());
            }
            int idx = imgUrl.lastIndexOf('/');
            return idx >= 0 ? imgUrl.substring(idx + 1) : imgUrl;
        }
        return imgUrl;
    }

    @Override
    public void resetCache2Redis(String imageKey) {
        redisTemplate.expire(imageKey,EXPIRE,TimeUnit.SECONDS);
    }
}

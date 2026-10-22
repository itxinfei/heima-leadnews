package com.xftt.common.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.green.model.v20180509.ImageSyncScanRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.xftt.common.common.contants.Contants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 图片审核
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aliyun")
@PropertySource("classpath:aliyun.properties")
public class AliyunImageScanRequest {

    private String accessKey;
    private String secret;

    public String imageScanRequest(List<String> images) throws Exception {
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, secret);
        DefaultProfile.addEndpoint("cn-shanghai", "cn-shanghai", "Green", "green.cn-shanghai.aliyuncs.com");
        IAcsClient client = new DefaultAcsClient(profile);

        ImageSyncScanRequest imageSyncScanRequest = new ImageSyncScanRequest();
        imageSyncScanRequest.setAcceptFormat(FormatType.JSON);
        imageSyncScanRequest.setMethod(MethodType.POST);
        imageSyncScanRequest.setEncoding("utf-8");
        imageSyncScanRequest.setProtocol(ProtocolType.HTTP);

        Map<String, Object> httpBody = new LinkedHashMap<>();
        httpBody.put("scenes", Arrays.asList("logo", "porn", "ad", "terrorism"));

        List<Map<String, Object>> tasks = new ArrayList<>();
        for (String image : images) {
            Map<String, Object> task = new LinkedHashMap<>();
            task.put("dataId", UUID.randomUUID().toString());
            task.put("url", image);
            task.put("time", new Date());
            tasks.add(task);
        }
        httpBody.put("tasks", tasks);

        ObjectMapper objectMapper = Contants.objectMapper;
        imageSyncScanRequest.setHttpContent(objectMapper.writeValueAsString(httpBody).getBytes(StandardCharsets.UTF_8),
                "UTF-8", FormatType.JSON);
        imageSyncScanRequest.setConnectTimeout(3000);
        imageSyncScanRequest.setReadTimeout(10000);

        HttpResponse httpResponse = client.doAction(imageSyncScanRequest);
        if (httpResponse != null && httpResponse.isSuccess()) {
            String body = new String(httpResponse.getHttpContent(), StandardCharsets.UTF_8);
            JsonNode scrResponse = objectMapper.readTree(body);
            if (scrResponse.path("code").asInt() == 200) {
                for (JsonNode taskResult : scrResponse.path("data")) {
                    if (taskResult.path("code").asInt() == 200) {
                        for (JsonNode sceneResult : taskResult.path("results")) {
                            String scene = sceneResult.path("scene").asString();
                            String suggestion = sceneResult.path("suggestion").asString();
                            System.out.println("scene = [" + scene + "]");
                            System.out.println("suggestion = [" + suggestion + "]");
                            return suggestion;
                        }
                    } else {
                        System.out.println("task process fail. task response:" + taskResult);
                    }
                }
            } else {
                System.out.println("the whole image scan request failed. response:" + scrResponse);
            }
        }
        return null;
    }
}

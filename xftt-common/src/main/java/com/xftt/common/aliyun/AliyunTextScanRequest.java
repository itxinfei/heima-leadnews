package com.xftt.common.aliyun;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.green.model.v20180509.TextScanRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
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
 * 文本审核
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "aliyun")
@PropertySource("classpath:aliyun.properties")
public class AliyunTextScanRequest {

    private String accessKey;
    private String secret;

    public String textScanRequest(String content) throws Exception {
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", accessKey, secret);
        IAcsClient client = new DefaultAcsClient(profile);
        TextScanRequest textScanRequest = new TextScanRequest();
        textScanRequest.setAcceptFormat(FormatType.JSON);
        textScanRequest.setHttpContentType(FormatType.JSON);
        textScanRequest.setMethod(com.aliyuncs.http.MethodType.POST);
        textScanRequest.setEncoding("UTF-8");
        textScanRequest.setRegionId("cn-shanghai");

        Map<String, Object> task = new LinkedHashMap<>();
        task.put("dataId", UUID.randomUUID().toString());
        task.put("content", content);

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("scenes", Arrays.asList("antispam"));
        data.put("tasks", List.of(task));

        ObjectMapper objectMapper = Contants.objectMapper;
        textScanRequest.setHttpContent(objectMapper.writeValueAsString(data).getBytes(StandardCharsets.UTF_8),
                "UTF-8", FormatType.JSON);
        textScanRequest.setConnectTimeout(3000);
        textScanRequest.setReadTimeout(6000);
        try {
            HttpResponse httpResponse = client.doAction(textScanRequest);
            if (httpResponse.isSuccess()) {
                String body = new String(httpResponse.getHttpContent(), StandardCharsets.UTF_8);
                JsonNode scrResponse = objectMapper.readTree(body);
                if (scrResponse.path("code").asInt() == 200) {
                    for (JsonNode taskResult : scrResponse.path("data")) {
                        if (taskResult.path("code").asInt() == 200) {
                            for (JsonNode sceneResult : taskResult.path("results")) {
                                String scene = sceneResult.path("scene").asString();
                                String suggestion = sceneResult.path("suggestion").asString();
                                System.out.println("args = [" + scene + "]");
                                System.out.println("args = [" + suggestion + "]");
                                return suggestion;
                            }
                        } else {
                            System.out.println("task process fail:" + taskResult.path("code").asInt());
                        }
                    }
                } else {
                    System.out.println("detect not success. code:" + scrResponse.path("code").asInt());
                }
            } else {
                System.out.println("response not success. status:" + httpResponse.getStatus());
            }
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}

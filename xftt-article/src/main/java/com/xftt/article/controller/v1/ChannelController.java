package com.xftt.article.controller.v1;

import com.xftt.model.admin.pojos.AdChannel;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.mappers.admin.AdChannelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * APP 端频道接口：供首页频道栏动态加载
 */
@RestController
@RequestMapping("/api/v1/channel")
public class ChannelController {

    @Autowired
    private AdChannelMapper adChannelMapper;

    @GetMapping("/list")
    public ResponseResult list() {
        List<AdChannel> channels = adChannelMapper.selectAll();
        List<Map<String, Object>> options = channels.stream()
                .filter(channel -> channel.getStatus() == null || channel.getStatus())
                .sorted((a, b) -> Integer.compare(a.getOrd() == null ? 0 : a.getOrd(), b.getOrd() == null ? 0 : b.getOrd()))
                .map(channel -> {
                    Map<String, Object> option = new HashMap<>();
                    option.put("id", channel.getId());
                    option.put("name", channel.getName());
                    return option;
                })
                .collect(Collectors.toList());
        return ResponseResult.okResult(options);
    }
}

package com.xinfei.media.service.impl;

import com.xinfei.media.service.AdChannelService;
import com.xinfei.model.admin.pojos.AdChannel;
import com.xinfei.model.mappers.admin.AdChannelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdChannelServiceImpl implements AdChannelService {

    @Autowired
    private AdChannelMapper adChannelMapper;

    @Override
    public List<AdChannel> selectAll() {
        return adChannelMapper.selectAll();
    }
}

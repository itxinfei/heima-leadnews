package com.xinfei.crawler.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.xinfei.crawler.service.CrawlerIpPoolService;
import com.xinfei.model.crawler.core.proxy.CrawlerProxy;
import com.xinfei.model.crawler.pojos.ClIpPool;
import com.xinfei.model.mappers.crawerls.ClIpPoolMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@SuppressWarnings("all")

public class CrawlerIpPoolServiceImpl implements CrawlerIpPoolService {

    @Autowired
    private ClIpPoolMapper clIpPoolMapper;

    @Override
    public void saveCrawlerIpPool(ClIpPool clIpPool) {
        clIpPoolMapper.insertSelective(clIpPool);
    }

    @Override
    public boolean checkExist(String host, int port) {
        ClIpPool clIpPool = new ClIpPool();
        clIpPool.setIp(host);
        clIpPool.setPort(port);
        List<ClIpPool> clIpPools = clIpPoolMapper.selectList(clIpPool);
        if(null!=clIpPools && !clIpPools.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public void updateCrawlerIpPool(ClIpPool clIpPool) {
        clIpPoolMapper.updateByPrimaryKey(clIpPool);
    }

    @Override
    public List<ClIpPool> queryList(ClIpPool clIpPool) {
        return clIpPoolMapper.selectList(clIpPool);
    }

    @Override
    public List<ClIpPool> queryAvailabelList(ClIpPool clIpPool) {
        return clIpPoolMapper.selectAvailableList(clIpPool);
    }

    @Override
    public void delete(ClIpPool clIpPool) {
        clIpPoolMapper.deleteByPrimaryKey(clIpPool.getId());
    }

    @Override
    public void unAvailabelProxy(CrawlerProxy proxy, String errorMsg) {
        ClIpPool clIpPool = new ClIpPool();
        clIpPool.setIp(proxy.getHost());
        clIpPool.setPort(proxy.getPort());
        clIpPool.setEnable(true);
        List<ClIpPool> clIpPools = clIpPoolMapper.selectList(clIpPool);
        if(null!=clIpPools && !clIpPools.isEmpty()){
            for (ClIpPool ipPool : clIpPools) {
                ipPool.setEnable(false);
                ipPool.setError(errorMsg);
                clIpPoolMapper.updateByPrimaryKey(ipPool);
            }
        }
    }
}

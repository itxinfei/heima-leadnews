package com.xinfei.migration.service.impl;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.xinfei.migration.service.ApArticleService;
import com.xinfei.model.article.pojos.ApArticle;
import com.xinfei.model.mappers.app.ApArticleMapper;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@SuppressWarnings("all")

public class ApArticleServiceImpl implements ApArticleService {

    @Autowired
    private ApArticleMapper apArticleMapper;

    @Override
    public ApArticle getById(Long id) {
        return apArticleMapper.selectById(id);
    }

    @Override
    public List<ApArticle> getUnSyncArticleList() {
        ApArticle apArticle = new ApArticle();
        apArticle.setSyncStatus(false);
        return apArticleMapper.selectList(apArticle);
    }

    @Override
    public void updateSyncStatus(ApArticle apArticle) {
        log.info("开始更新数据同步状态,aparticle:{}",apArticle);
        if(null != apArticle){
            apArticle.setSyncStatus(true);
            apArticleMapper.updateSyncStatus(apArticle);
        }
    }
}

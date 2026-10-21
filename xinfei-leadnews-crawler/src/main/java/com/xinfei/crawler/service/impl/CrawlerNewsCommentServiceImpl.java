package com.xinfei.crawler.service.impl;

import com.xinfei.crawler.service.CrawlerNewsCommentService;
import com.xinfei.model.crawler.pojos.ClNewsComment;
import com.xinfei.model.mappers.crawerls.ClNewsCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("all")
public class CrawlerNewsCommentServiceImpl implements CrawlerNewsCommentService {

    @Autowired
    private ClNewsCommentMapper clNewsCommentMapper;

    @Override
    public void saveClNewsComment(ClNewsComment clNewsComment) {
        clNewsCommentMapper.insertSelective(clNewsComment);
    }
}

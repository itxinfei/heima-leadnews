package com.xftt.migration.service.impl;

import com.xftt.migration.service.ApArticleContentService;
import com.xftt.model.article.pojos.ApArticleContent;
import com.xftt.model.mappers.app.ApArticleContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("all")
public class ApArticleContentServiceImpl implements ApArticleContentService {

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Override
    public List<ApArticleContent> queryByArticleIds(List<String> ids) {
        return apArticleContentMapper.selectByArticleIds(ids);
    }

    @Override
    public ApArticleContent getByArticleId(Integer id) {
        return apArticleContentMapper.selectByArticleId(id);
    }
}

package com.xinfei.migration.service;

import com.xinfei.model.article.pojos.ApAuthor;

import java.util.List;

public interface ApAuthorService {

    public List<ApAuthor> queryByIds(List<Integer> ids);

    public ApAuthor getById(Long id);
}

package com.xftt.model.mappers.app;

import com.xftt.model.article.pojos.ApAuthor;

import java.util.List;

public interface ApAuthorMapper {
    ApAuthor selectById(Integer id);

    ApAuthor selectByAuthorName(String authorName);
    void insert(ApAuthor apAuthor);

    List<ApAuthor> selectByIds(List<Integer> ids);
}
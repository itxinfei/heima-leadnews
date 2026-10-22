package com.xftt.model.mappers.app;

import com.xftt.model.article.dtos.ArticleHomeDto;
import com.xftt.model.user.pojos.ApUser;
import com.xftt.model.user.pojos.ApUserArticleList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApUserArticleListMapper {
    List<ApUserArticleList> loadArticleIdListByUser(@Param("user") ApUser user,@Param("dto") ArticleHomeDto dto,@Param("type") Short type);
}

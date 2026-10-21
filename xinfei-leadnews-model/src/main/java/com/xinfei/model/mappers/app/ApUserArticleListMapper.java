package com.xinfei.model.mappers.app;

import com.xinfei.model.article.dtos.ArticleHomeDto;
import com.xinfei.model.user.pojos.ApUser;
import com.xinfei.model.user.pojos.ApUserArticleList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApUserArticleListMapper {
    List<ApUserArticleList> loadArticleIdListByUser(@Param("user") ApUser user,@Param("dto") ArticleHomeDto dto,@Param("type") Short type);
}

package com.xftt.model.mappers.app;

import com.xftt.model.article.pojos.ApComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApCommentMapper {

    /**
     * 按文章查询评论（含作者昵称）
     */
    List<ApComment> selectByEntryId(@Param("entryId") Integer entryId);

    /**
     * 新增评论
     */
    void insert(ApComment apComment);

    /**
     * 评论点赞数 +1
     */
    int addLike(@Param("id") Integer id);
}

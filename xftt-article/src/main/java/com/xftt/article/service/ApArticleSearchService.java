package com.xftt.article.service;

import com.xftt.model.article.dtos.UserSearchDto;
import com.xftt.model.common.dtos.ResponseResult;

public interface ApArticleSearchService {

    /**
     * 查询搜索历史
     * @param dto
     * @return
     */
    ResponseResult findUserSearch(UserSearchDto dto);

    /**
     * 删除搜索历史
     * @param dto
     * @return
     */
    ResponseResult delUserSearch(UserSearchDto dto);

    /**
     * 清空搜索历史记录
     * @param dto
     * @return
     */
    ResponseResult clearUserSearch(UserSearchDto dto);

    /**
     * 今日热词
     * @param date
     * @return
     */
    ResponseResult hotkeywords(String date);

    /**
     * 模糊查询联想词
     * @param dto
     * @return
     */
    ResponseResult searchAssociate(UserSearchDto dto);

    /**
     * es文章分页查询
     * @param dto
     * @return
     */
    ResponseResult esArticleSearch(UserSearchDto dto);

    /**
     * 保存搜索记录
     * @param entryId
     * @param searchWords
     * @return
     */
    ResponseResult saveUserSearch(Integer entryId,String searchWords);

    /**
     * 搜索联想词 v2
     * @param dto
     * @return
     */
    ResponseResult searchAssocicationV2(UserSearchDto dto);

    /**
     * 存量文章回填 ES 索引（BE-F04）：索引缺失时自动创建，按 id 翻批全量写入
     * @return 回填文章数
     */
    ResponseResult backfillIndex(Long lastId);
}

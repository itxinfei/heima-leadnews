package com.xftt.model.user.dtos;

import com.xftt.model.annotation.IdEncrypt;
import lombok.Data;

/**
 * 收藏行为入参（APP-F10）
 */
@Data
public class CollectionDto {

    /** 文章ID */
    @IdEncrypt
    private Integer articleId;

    /**
     * 操作方式
     * 0  收藏
     * 1  取消收藏
     */
    private Short operation;
}

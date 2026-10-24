package com.xftt.model.media.dtos;

import lombok.Data;

/**
 * 管理端人工审核操作 DTO。驳回(reject)时必须填写 reason。
 */
@Data
public class WmNewsReviewDto {

    /** wm_news 主键 */
    private Integer id;
    /** 驳回理由（reject 时必填） */
    private String reason;
}

package com.xinfei.media.service;

import com.xinfei.model.common.dtos.ResponseResult;
import com.xinfei.model.media.dtos.WmNewsDto;
import com.xinfei.model.media.dtos.WmNewsPageReqDto;
import org.springframework.web.bind.annotation.ResponseBody;

public interface NewsService {

    /**
     * 保存或修改
     * @param dto
     * @param type
     * @return
     */
    public ResponseResult saveNews(WmNewsDto dto,Short type);

    /**
     * 查询文章列表
     * @param dto
     * @return
     */
    public ResponseResult listByUser(WmNewsPageReqDto dto);

    /**
     * 根据当前主键查询文章
     * @param dto
     * @return
     */
    public ResponseResult findWmNewsById(WmNewsDto dto);

    /**
     * 根据主键删除文章信息
     * @param dto
     * @return
     */
    public ResponseResult delNews(WmNewsDto dto);
}

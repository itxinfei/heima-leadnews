package com.xftt.media.service;

import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.media.dtos.WmMaterialDto;
import com.xftt.model.media.dtos.WmMaterialListDto;
import org.springframework.web.multipart.MultipartFile;

public interface MaterialService {

    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    ResponseResult uploadPicture(MultipartFile multipartFile);

    /**
     * 删除图片
     * @param dto
     * @return
     */
    ResponseResult delPicture(WmMaterialDto dto);

    /**
     * 分页查询列表
     * @param dto
     * @return
     */
    ResponseResult findList(WmMaterialListDto dto);

    /**
     * 收藏或取消收藏
     * @param dto
     * @param type
     * @return
     */
    ResponseResult changeUserMaterialStatus(WmMaterialDto dto,Short type);
}

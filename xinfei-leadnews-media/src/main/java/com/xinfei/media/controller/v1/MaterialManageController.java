package com.xinfei.media.controller.v1;

import com.xinfei.common.media.constans.WmMediaConstans;
import com.xinfei.media.apis.MaterialManageControllerApi;
import com.xinfei.media.service.MaterialService;
import com.xinfei.model.common.dtos.ResponseResult;
import com.xinfei.model.media.dtos.WmMaterialDto;
import com.xinfei.model.media.dtos.WmMaterialListDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/media/material")
public class MaterialManageController implements MaterialManageControllerApi {

    @Autowired
    private MaterialService materialService;

    @Override
    @PostMapping("/upload_picture")
    public ResponseResult uploadPicture(MultipartFile file) {
        return materialService.uploadPicture(file);
    }

    @Override
    @PostMapping("/del_picture")
    public ResponseResult delPicture(@RequestBody WmMaterialDto dto) {
        return materialService.delPicture(dto);
    }

    @Override
    @PostMapping("/list")
    public ResponseResult list(@RequestBody WmMaterialListDto dto) {
        return materialService.findList(dto);
    }

    @Override
    @PostMapping("/collect")
    public ResponseResult collectionMaterial(@RequestBody WmMaterialDto dto) {
        return materialService.changeUserMaterialStatus(dto, WmMediaConstans.COLLECT_MATERIAL);
    }

    @Override
    @PostMapping("/cancel_collect")
    public ResponseResult cancelCollectionMaterial(@RequestBody WmMaterialDto dto) {
        return materialService.changeUserMaterialStatus(dto,WmMediaConstans.CANCEL_COLLECT_MATERIAL);
    }
}

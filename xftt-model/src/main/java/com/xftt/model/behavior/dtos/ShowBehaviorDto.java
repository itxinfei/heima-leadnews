package com.xftt.model.behavior.dtos;


import com.xftt.model.annotation.IdEncrypt;
import com.xftt.model.article.pojos.ApArticle;
import lombok.Data;

import java.util.List;

@Data
public class ShowBehaviorDto {

    // 设备ID
    @IdEncrypt
    Integer equipmentId;
    List<ApArticle> articleIds;

}

package com.xinfei.model.behavior.dtos;


import com.xinfei.model.annotation.IdEncrypt;
import com.xinfei.model.article.pojos.ApArticle;
import lombok.Data;

import java.util.List;

@Data
public class ShowBehaviorDto {

    // 设备ID
    @IdEncrypt
    Integer equipmentId;
    List<ApArticle> articleIds;

}

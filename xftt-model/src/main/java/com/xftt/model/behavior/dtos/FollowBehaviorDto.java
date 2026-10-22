package com.xftt.model.behavior.dtos;

import com.xftt.model.annotation.IdEncrypt;
import lombok.Data;

@Data
public class FollowBehaviorDto {
    // 设备ID
    @IdEncrypt
    Integer equipmentId;
    // 文章ID
    @IdEncrypt
    Integer articleId;
    @IdEncrypt
    Integer followId;
}

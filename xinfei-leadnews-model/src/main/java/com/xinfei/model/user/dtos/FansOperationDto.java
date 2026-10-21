package com.xinfei.model.user.dtos;

import com.xinfei.model.annotation.IdEncrypt;
import lombok.Data;

@Data
public class FansOperationDto {
    @IdEncrypt
    private Integer fansId;

    /**
     * true 开   false 关
     */
    private Boolean switchState;
}

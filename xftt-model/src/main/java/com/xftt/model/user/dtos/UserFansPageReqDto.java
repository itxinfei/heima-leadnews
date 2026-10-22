package com.xftt.model.user.dtos;

import com.xftt.model.common.dtos.PageRequestDto;
import lombok.Data;

@Data
public class UserFansPageReqDto extends PageRequestDto {
    private String fansName;
    private Short level;
}

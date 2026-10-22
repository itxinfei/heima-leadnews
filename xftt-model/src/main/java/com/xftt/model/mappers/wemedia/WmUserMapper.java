package com.xftt.model.mappers.wemedia;

import com.xftt.model.media.pojos.WmUser;

public interface WmUserMapper {
    WmUser selectByName(String name);

    WmUser selectById(Long id);
}
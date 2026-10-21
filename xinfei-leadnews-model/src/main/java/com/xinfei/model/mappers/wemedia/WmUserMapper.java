package com.xinfei.model.mappers.wemedia;

import com.xinfei.model.media.pojos.WmUser;

public interface WmUserMapper {
    WmUser selectByName(String name);

    WmUser selectById(Long id);
}
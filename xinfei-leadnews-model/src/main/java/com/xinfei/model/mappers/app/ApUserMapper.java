package com.xinfei.model.mappers.app;

import com.xinfei.model.user.pojos.ApUser;

public interface ApUserMapper {
    ApUser selectById(Integer id);

    ApUser selectByApPhone(String phone);
}
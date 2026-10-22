package com.xftt.model.mappers.app;

import com.xftt.model.user.pojos.ApUser;

public interface ApUserMapper {
    ApUser selectById(Integer id);

    ApUser selectByApPhone(String phone);
}
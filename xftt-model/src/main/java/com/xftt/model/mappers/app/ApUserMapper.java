package com.xftt.model.mappers.app;

import com.xftt.model.user.pojos.ApUser;

public interface ApUserMapper {
    ApUser selectById(Integer id);

    ApUser selectByApPhone(String phone);

    /** 资料编辑（APP-F12）：仅更新昵称/头像/性别 */
    int updateProfile(ApUser user);
}
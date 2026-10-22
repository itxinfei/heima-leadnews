package com.xftt.model.mappers.admin;

import com.xftt.model.admin.pojos.AdUser;

public interface AdUserMapper {

    AdUser selectByName(String name);
}
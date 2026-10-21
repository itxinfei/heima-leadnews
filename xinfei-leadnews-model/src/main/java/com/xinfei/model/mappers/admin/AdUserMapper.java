package com.xinfei.model.mappers.admin;

import com.xinfei.model.admin.pojos.AdUser;

public interface AdUserMapper {

    AdUser selectByName(String name);
}
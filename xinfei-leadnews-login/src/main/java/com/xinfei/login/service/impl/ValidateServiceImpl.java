package com.xinfei.login.service.impl;

import com.xinfei.login.service.ValidateService;
import com.xinfei.model.user.pojos.ApUser;
import com.xinfei.utils.common.DESUtils;
import com.xinfei.utils.common.MD5Utils;
import org.springframework.stereotype.Service;

@Service
public class ValidateServiceImpl implements ValidateService {
    @Override
    public boolean validateDES(ApUser user, ApUser db) {
        if(db.getPassword().equals(DESUtils.encode(user.getPassword()))){
            return true;
        }
        return false;
    }

    @Override
    public boolean validateMD5(ApUser user, ApUser db) {
        if(db.getPassword().equals(MD5Utils.encode(user.getPassword()))){
            return true;
        }
        return false;
    }

    @Override
    public boolean validateWithSalt(ApUser user, ApUser db) {
        if(db.getPassword().equals(MD5Utils.encodeWithSalt(user.getPassword(),db.getSalt()))){
            return true;
        }
        return false;
    }
}

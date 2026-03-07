package com.mobilele.service;

import com.mobilele.model.entity.Roles;
import com.mobilele.model.enums.RoleEnums;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    void addAllRoleEnumsInRepository();
    Roles setDefaultRoleOnNewUser();
}

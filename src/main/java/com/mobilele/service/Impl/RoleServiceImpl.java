package com.mobilele.service.Impl;

import com.mobilele.model.entity.Roles;
import com.mobilele.model.enums.RoleEnums;
import com.mobilele.repository.RoleRepository;
import com.mobilele.repository.UserRepository;
import com.mobilele.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void addAllRoleEnumsInRepository(){
        if (this.roleRepository.count()==0){
            for (RoleEnums roleEnum : RoleEnums.values()) {
                Roles role = new Roles();
                role.setRoleName(roleEnum);
                this.roleRepository.save(role);
            }
        }
        else {
            RoleEnums[] Roles = RoleEnums.values();
            for (RoleEnums roleEnum : Roles) {
                if (roleRepository.findByRoleName(roleEnum).isEmpty()){
                    Roles role = new Roles();
                    role.setRoleName(modelMapper.map(roleEnum.name(), Roles.class).getRoleName());
                    this.roleRepository.save(role);
                }
            }
        }
    }

    @Override
    public Roles setDefaultRoleOnNewUser() {
        if (userRepository.count() == 0) {
            // First registered user - ADMIN
            return this.roleRepository.findByRoleName(RoleEnums.ADMIN).get();
        } else {
            //All next - USER
           return this.roleRepository.findByRoleName (RoleEnums.USER).get();
        }
    }
}

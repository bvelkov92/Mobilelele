package com.mobilele.service.Impl;

import com.mobilele.model.DTOs.User.UserRegister;
import com.mobilele.model.entity.Users;
import com.mobilele.repository.UserRepository;
import com.mobilele.service.RoleService;
import com.mobilele.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void userRegister(UserRegister userRegisterDto){
        this.roleService.addAllRoleEnumsInRepository();
        Users newUser = new Users();
        newUser.setUsername(userRegisterDto.getUsername());
        newUser.setEmail(userRegisterDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
        newUser.setRole(this.roleService.setDefaultRoleOnNewUser());

        this.userRepository.saveAndFlush(this.modelMapper.map(newUser, Users.class));
    }

}


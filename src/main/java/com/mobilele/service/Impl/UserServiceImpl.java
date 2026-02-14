package com.mobilele.service.Impl;

import com.mobilele.model.DTOs.UserLoginDTO;
import com.mobilele.model.DTOs.UserRegisterDto;
import com.mobilele.model.entity.User;
import com.mobilele.repository.RoleRepository;
import com.mobilele.repository.UserRepository;
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
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void userRegister(UserRegisterDto userRegisterDto){
        User newUser = new User();
        newUser.setUsername(userRegisterDto.getUsername());
        newUser.setEmail(userRegisterDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
// TODO: ADD ROLES CONDITION
        this.userRepository.saveAndFlush(this.modelMapper.map(newUser, User.class));
    }

}

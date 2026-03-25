package com.mobilele.service.Impl;

import com.mobilele.model.DTOs.User.UserRegister;
import com.mobilele.model.DTOs.User.ViewAllUsersDto;
import com.mobilele.model.entity.Users;
import com.mobilele.repository.OfferRepository;
import com.mobilele.repository.UserRepository;
import com.mobilele.service.RoleService;
import com.mobilele.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final OfferRepository offerRepository;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleService roleService, PasswordEncoder passwordEncoder, OfferRepository offerRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.offerRepository = offerRepository;
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

    @Override
    public Set<ViewAllUsersDto> getAllUsers() {
       return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, ViewAllUsersDto.class))
                .collect(Collectors.toCollection(LinkedHashSet::new));

    }

    public Long getUserId() throws NullPointerException {
        Authentication getLoggedUser = SecurityContextHolder.getContext().getAuthentication();
        Long loggedUserId = this.userRepository.findUserByEmail(getLoggedUser.getName()).orElseThrow(NullPointerException::new).getId();

        if(loggedUserId!=null){
      return loggedUserId;
      }else {
            throw new NullPointerException("Not logged user!");
        }
    }

    @Override
    public void deleteUserWithId(Long id) {
        Users user = this.userRepository.findById(id).orElse(null);
        this.offerRepository.deleteAllBySeller(user);
        this.userRepository.deleteById(id);
    }

}


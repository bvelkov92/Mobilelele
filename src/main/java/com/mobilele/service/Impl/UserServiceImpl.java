package com.mobilele.service.Impl;

import com.mobilele.model.DTOs.User.ChangePassword;
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
    public void userRegister(UserRegister userRegisterDto) {
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

    public Long getAuthenticatedUserId() throws NullPointerException {

        Authentication getLoggedUser = SecurityContextHolder.getContext().getAuthentication();


        if (getLoggedUser != null) {
            return this.userRepository.findUserByEmail(getLoggedUser.getName())
                    .orElseThrow(NullPointerException::new).getId();
        } else {
            throw new NullPointerException("Not logged user!");
        }
    }


    @Override
    public void deleteUserWithId(Long id) {
        Users user = this.userRepository.findById(id).orElse(null);
        this.offerRepository.deleteAllBySeller(user);
        this.userRepository.deleteById(id);
    }

    @Override
    public Users getAuthenticatedUser() {
        Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();

        if (authenticatedUser!=null){
        String email = authenticatedUser.getName().trim();
        Long id = this.userRepository.findUserByEmail(email).orElseThrow(()->
                        new NullPointerException("There is not logged user!"))
                                                        .getId();
        return this.userRepository.findById(id).orElseThrow(() -> new NullPointerException("Not found user"));
    }else {
            throw new RuntimeException();
        }
    }

    @Override
    public Users getUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public void changePassword(ChangePassword changePasswordDto) {
        Authentication logged = SecurityContextHolder.getContext().getAuthentication();

        if (logged != null) {
            String loggedUser = logged.getName();
            Users foundUser = this.userRepository.findUserByEmail(loggedUser).orElse(null);

            if (foundUser == null || !passwordEncoder.matches( changePasswordDto.getPassword(),foundUser.getPassword())) {
                throw new RuntimeException();
            } else {
                foundUser.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
                this.userRepository.saveAndFlush(foundUser);
            }
        } else {
            throw new RuntimeException();
        }
    }
}


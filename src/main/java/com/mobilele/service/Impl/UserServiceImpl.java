package com.mobilele.service.Impl;

import com.mobilele.model.DTOs.User.ChangePassword;
import com.mobilele.model.DTOs.User.EditProfile;
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
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
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

        if (getLoggedUser() != null) {
            return this.userRepository.findUserByEmail(getLoggedUser().getEmail())
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
    public Users getUserById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public void changePassword(ChangePassword changePasswordDto) {
        Users foundUser = getLoggedUser();
        if (foundUser == null || !passwordEncoder.matches(changePasswordDto.getPassword(),foundUser.getPassword())) {
                throw new RuntimeException();
            } else {
                foundUser.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
                this.userRepository.saveAndFlush(foundUser);
            }
    }

    @Override
    public void updateProfile(EditProfile editProfile, MultipartFile file) {
        Users loggedUser = getLoggedUser();
        Users user = this.userRepository.findUserByUsername(loggedUser.getUsername()).orElseThrow();
                user.setFirstName(editProfile.getFirstName());
                user.setLastName(editProfile.getLastName());
                user.setAge(editProfile.getAge());
                user.setEmail(editProfile.getEmail());

                if (file!=null && !file.isEmpty()) {
                    String profileImg = UUID.randomUUID() + "_" + file.getOriginalFilename();
                    Path path = Paths.get("uploads/" + profileImg);
                    try {
                        Files.createDirectories(path.getParent());
                        Files.copy(file.getInputStream(), path);
                        user.setImageUrl(profileImg);

                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        this.userRepository.save(user);
    }

    @Override
    public EditProfile getEditProfileDto() {
        Users loggedUser = getLoggedUser();
        EditProfile editProfile = modelMapper.map(loggedUser, EditProfile.class);
        editProfile.setRole(loggedUser.getRole());
        editProfile.setUsername(loggedUser.getUsername());
        return editProfile;
    }


    public Users getLoggedUser(){
        Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();
        return this.userRepository.findUserByUsername(loggedUser.getName()).orElse(null);
    }
}


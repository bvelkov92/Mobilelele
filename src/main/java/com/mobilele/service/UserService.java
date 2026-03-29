package com.mobilele.service;

import com.mobilele.model.DTOs.User.ChangePassword;
import com.mobilele.model.DTOs.User.EditProfile;
import com.mobilele.model.DTOs.User.UserRegister;
import com.mobilele.model.DTOs.User.ViewAllUsersDto;
import com.mobilele.model.entity.Users;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface UserService {

    void userRegister(UserRegister userRegisterDto);
    Set<ViewAllUsersDto> getAllUsers ();
    Long getAuthenticatedUserId();
    void deleteUserWithId(Long id);
    Users getUserById(Long id);
    Users getLoggedUser();
    void changePassword(ChangePassword changePasswordDto);
    void updateProfile(EditProfile editProfile, MultipartFile file);

    EditProfile getEditProfileDto();
}

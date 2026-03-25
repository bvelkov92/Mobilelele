package com.mobilele.service;

import com.mobilele.model.DTOs.User.UserRegister;
import com.mobilele.model.DTOs.User.ViewAllUsersDto;

import java.util.Set;

public interface UserService {

    void userRegister(UserRegister userRegisterDto);
    Set<ViewAllUsersDto> getAllUsers ();
    Long getUserId();
    void deleteUserWithId(Long id);
}

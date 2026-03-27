package com.mobilele.model.DTOs.User;

import com.mobilele.model.entity.Roles;
import com.mobilele.model.entity.Users;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditProfile {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Integer age;
    private String role;


}

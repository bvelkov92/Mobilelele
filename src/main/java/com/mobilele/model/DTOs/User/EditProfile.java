package com.mobilele.model.DTOs.User;

import com.mobilele.model.entity.Roles;
import com.mobilele.utils.anotations.IsExistUsernameValidation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EditProfile {

    @IsExistUsernameValidation
    private String username;

    private String email;

    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;

    @Min(6)
    private Integer age;

    private Roles role;



}

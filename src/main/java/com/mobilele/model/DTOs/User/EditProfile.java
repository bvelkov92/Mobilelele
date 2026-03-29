package com.mobilele.model.DTOs.User;

import com.mobilele.model.entity.Roles;
import com.mobilele.utils.anotations.IsExistEmailValidation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@IsExistEmailValidation
public class EditProfile {


    private String username;

    private String email;

    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;

    @Min(10)
    private Integer age;

    private Roles role;



}

package com.mobilele.model.entity;

import com.mobilele.model.enums.RoleEnums;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Roles extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleEnums roleName;

    public Roles(RoleEnums roleName) {
        this.roleName = roleName;
    }
}

package com.mobilele.model.entity;

import com.mobilele.model.enums.RoleEnums;
import jakarta.persistence.*;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleEnums roleName;

    public Role(RoleEnums roleName) {
        this.roleName = roleName;
    }
}

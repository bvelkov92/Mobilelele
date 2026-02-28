package com.mobilele.repository;


import com.mobilele.model.entity.Roles;
import com.mobilele.model.enums.RoleEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {


    Optional<Roles> findByRoleName(RoleEnums roleName);


}
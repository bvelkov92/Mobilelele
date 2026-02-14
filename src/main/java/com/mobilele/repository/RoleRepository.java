package com.mobilele.repository;


import com.mobilele.model.entity.Role;
import com.mobilele.model.enums.RoleEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {


    Optional<Role> findByRoleName(RoleEnums roleName);


}
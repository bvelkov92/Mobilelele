package com.mobilele.service.Impl;

import com.mobilele.model.entity.Role;
import com.mobilele.model.enums.RoleEnums;
import com.mobilele.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RoleServiceImpl implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            // Добавяме ADMIN
            Role admin = new Role();
            admin.setRoleName(RoleEnums.ADMIN);
            roleRepository.save(admin);

            Role user = new Role();
            user.setRoleName(RoleEnums.USER);
            roleRepository.save(user);

            System.out.println("Roles seeded successfully!");
        }
    }
}

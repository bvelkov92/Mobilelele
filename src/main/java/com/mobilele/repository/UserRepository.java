package com.mobilele.repository;

import com.mobilele.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

    Optional<Users> findUserByEmail(String email);
}

package com.mobilele.repository;

import com.mobilele.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findUsersByUsername(String username);

    Optional<User> findUserByEmail(String email);

}

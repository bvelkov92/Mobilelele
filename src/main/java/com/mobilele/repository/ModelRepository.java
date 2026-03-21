package com.mobilele.repository;

import com.mobilele.model.entity.Models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModelRepository extends JpaRepository <Models, Long > {


    Optional<Models> findByName(String name);

    void deleteAllByBrand_Id(Long id);
}
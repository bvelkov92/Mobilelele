package com.mobilele.repository;

import com.mobilele.model.entity.Models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ModelRepository extends JpaRepository <Models, Long > {


    Optional<Models> findByName(String name);

    void deleteAllByBrand_Id(Long id);

    Set<Models> findAllByBrand_Id(Long id);
    void deleteByIdAndBrand_Id(Long modelId, Long brandId);

    @Query("SELECT m FROM Models m WHERE m.id= :modelId AND m.brand.id = :brandId")
    Models findByIdAndBrand_Id(@Param("modelId") Long mId, @Param("brandId") Long bId);

}
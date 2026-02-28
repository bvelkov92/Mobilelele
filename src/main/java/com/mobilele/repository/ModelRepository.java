package com.mobilele.repository;

import com.mobilele.model.entity.Brands;
import com.mobilele.model.entity.Models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository <Models, Long > {

    List<Models> findByBrand(Brands brand);
    List<Models> findAllByBrand_Id(Long id);

}
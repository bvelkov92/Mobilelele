package com.mobilele.repository;

import com.mobilele.model.entity.Brand;
import com.mobilele.model.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository <Model, Long > {

    List<Model> findByBrands (Brand brand);
    List<Model> findAllByBrandsId (Long id);
}

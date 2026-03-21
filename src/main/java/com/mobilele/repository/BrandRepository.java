package com.mobilele.repository;


import com.mobilele.model.entity.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brands, Long> {

   @Override
   List<Brands> findAll();
   Optional <Brands> findByName(String brandName);

   @Query("SELECT DISTINCT brand FROM Brands brand LEFT JOIN FETCH brand.models")
   List<Brands> findAllWithModels();

   @Query("SELECT DISTINCT brand FROM Brands brand LEFT JOIN FETCH brand.models WHERE brand.id = :id")
   Optional <Brands> findBrandAndModelsById(Long id);

   Optional<Brands> findByIdAndModels_id(Long modelId, Long brandId);
}

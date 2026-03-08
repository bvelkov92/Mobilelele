package com.mobilele.repository;


import com.mobilele.model.DTOs.Brand.AddNewBrand;
import com.mobilele.model.entity.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;

import java.util.List;
import java.util.Optional;

public interface BrandRepository extends JpaRepository<Brands, Long> {

   @Override
   List<Brands> findAll();

   Optional <Brands> findByName(String brandName);
}

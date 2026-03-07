package com.mobilele.repository;


import com.mobilele.model.entity.Brands;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brands, Long> {

   @Override
   List<Brands> findAll();
}

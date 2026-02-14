package com.mobilele.repository;

import com.mobilele.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

   void findByName(String name);
}

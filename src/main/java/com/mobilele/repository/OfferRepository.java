package com.mobilele.repository;

import com.mobilele.model.entity.Offers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offers, Long> {


    void deleteAllByModel_Brand_Id(Long id);

}

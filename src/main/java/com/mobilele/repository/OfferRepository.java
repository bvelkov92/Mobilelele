package com.mobilele.repository;

import com.mobilele.model.entity.Offers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offers, Long> {

}

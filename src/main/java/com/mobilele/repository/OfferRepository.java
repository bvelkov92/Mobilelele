package com.mobilele.repository;

import com.mobilele.model.entity.Offers;
import com.mobilele.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offers, Long> {


    void deleteAllByModel_Brand_Id(Long id);
    void deleteAllBySeller(Users user);
}

package com.mobilele.service;

import com.mobilele.model.DTOs.Offer.SaveOffer;
import com.mobilele.model.DTOs.Offer.GetAllOffers;
import com.mobilele.model.DTOs.Offer.CurrentOfferDetails;
import com.mobilele.model.DTOs.Offer.UpdateOffer;

import java.util.List;

public interface OfferService {

    void saveOffer(SaveOffer saveOffer);
    List <GetAllOffers> getAllOffers();
    CurrentOfferDetails getDetails(Long id);
    void updateOffer(UpdateOffer updateOffer, Long id);
    UpdateOffer getUpdateDtoDetails(Long id);
    void deleteOffer( Long id);
}

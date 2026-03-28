package com.mobilele.service;

import com.mobilele.model.DTOs.Offer.SaveOffer;
import com.mobilele.model.DTOs.Offer.GetAllOffers;
import com.mobilele.model.DTOs.Offer.CurrentOfferDetails;
import com.mobilele.model.DTOs.Offer.UpdateOffer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OfferService {

    void saveOffer(SaveOffer saveOffer, MultipartFile file);
    List <GetAllOffers> getAllOffers();
    CurrentOfferDetails getDetails(Long id);
    void updateOffer(UpdateOffer updateOffer, Long id, MultipartFile file);
    UpdateOffer getUpdateDtoDetails(Long id);
    void deleteOffer( Long id);
}

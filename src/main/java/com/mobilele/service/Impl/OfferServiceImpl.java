package com.mobilele.service.Impl;

import com.mobilele.model.DTOs.Offer.SaveOffer;
import com.mobilele.model.DTOs.Offer.CurrentOfferDetails;
import com.mobilele.model.DTOs.Offer.GetAllOffers;
import com.mobilele.model.DTOs.Offer.UpdateOffer;
import com.mobilele.model.entity.Models;
import com.mobilele.model.entity.Offers;
import com.mobilele.model.entity.Users;
import com.mobilele.repository.ModelRepository;
import com.mobilele.repository.OfferRepository;
import com.mobilele.repository.UserRepository;
import com.mobilele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }
    public Users getLoggedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.userRepository.findUserByEmail(authentication.getName()).orElse(null);
    }

    @Override
    public void saveOffer(SaveOffer saveOffer) {
        Offers offer = new Offers();
        Models model = this.modelRepository.findById(saveOffer.getModelId()).get();

        offer.setDescription(saveOffer.getDescription());
        offer.setPrice(saveOffer.getPrice());
        offer.setEngine(saveOffer.getEngine());
        offer.setMileage(saveOffer.getMileage());
        offer.setModel(model);
        offer.setTransmission(saveOffer.getTransmissionType());
        offer.setYear(saveOffer.getYear());
        offer.setImageUrl(saveOffer.getImageUrl());
        if (getLoggedUser()!=null){
            Users user = getLoggedUser();
        offer.setSeller(user);
        }else {
            throw new NullPointerException("Not logged user!");
        }

        this.offerRepository.saveAndFlush(offer);

    }

    @Override
    public List<GetAllOffers> getAllOffers() {

        return this.offerRepository.findAll().stream()
                .map(offer -> {
                    GetAllOffers dto = new GetAllOffers();
                    dto.setMileage(offer.getMileage());
                    dto.setPrice(offer.getPrice());
                    dto.setImageUrl(offer.getImageUrl());
                    dto.setYear(offer.getYear());
                    dto.setEngine(offer.getEngine());
                    dto.setTransmission(offer.getTransmission());
                    dto.setBrand(offer.getModel().getBrand().getName());
                    dto.setModel(offer.getModel().getName());
                    dto.setId(offer.getId());
                    return dto;
                }).toList();
    }

    @Override
    public CurrentOfferDetails getDetails(Long id) {
        Offers offer = this.offerRepository.findById(id).orElse(null);
        if (offer != null) {
            String user = offer.getSeller().getUsername();
            CurrentOfferDetails mappedOffer = this.modelMapper.map(offer, CurrentOfferDetails.class);
            mappedOffer.setUser(user);

            return mappedOffer;
        }
        throw new NullPointerException();
        }

    @Override
    public void updateOffer(UpdateOffer updatedValues, Long id) {
        Offers currentOffer = this.offerRepository.findById(id).orElseThrow();
        Models model = this.modelRepository.findById(id).orElseThrow();
        modelMapper.map(updatedValues, currentOffer);
        currentOffer.setModel(model);
        this.offerRepository.saveAndFlush(currentOffer);
    }

    @Override
    public UpdateOffer getUpdateDtoDetails(Long id) {
        Offers offer = this.offerRepository.findById(id).orElseThrow();
        return modelMapper.map(offer, UpdateOffer.class);
    }

    @Override
    public void deleteOffer(Long id) {
        this.offerRepository.deleteById(id);
    }

}

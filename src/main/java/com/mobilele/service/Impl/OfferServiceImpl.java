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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
    public void saveOffer(SaveOffer saveOffer, MultipartFile file) {
        Offers offer = new Offers();
        Models model = this.modelRepository.findById(saveOffer.getModelId()).get();

        offer.setDescription(saveOffer.getDescription());
        offer.setPrice(saveOffer.getPrice());
        offer.setEngine(saveOffer.getEngine());
        offer.setMileage(saveOffer.getMileage());
        offer.setModel(model);
        offer.setTransmission(saveOffer.getTransmissionType());
        offer.setYear(saveOffer.getYear());
//==================== IMAGE UPLOAD ==========================
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path path = Paths.get("uploads/" + fileName);
        try {
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("File upload failed");
        }
        offer.setImageUrl(fileName);
        //================= SELLER SET =================

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

    @Transactional
    @Override
    public void updateOffer(UpdateOffer updatedValues, Long id, MultipartFile file) {
        Offers currentOffer = this.offerRepository.findById(id).orElseThrow();
        Models model = this.modelRepository.findById(updatedValues.getModelId()).orElseThrow();
        modelMapper.map(updatedValues, currentOffer);
        currentOffer.setModel(model);

        String imageName = UUID.randomUUID()+"_" +file.getOriginalFilename();
        Path path = Paths.get("uploads/" + imageName);

        try {
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("File upload failed");
        }
        currentOffer.setImageUrl(imageName);

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

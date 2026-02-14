package com.mobilele.service.Impl;

import com.mobilele.repository.OfferRepository;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl {

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


}

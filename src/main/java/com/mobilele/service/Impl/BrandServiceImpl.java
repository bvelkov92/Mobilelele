package com.mobilele.service.Impl;

import com.mobilele.model.DTOs.Brand.AddNewBrand;
import com.mobilele.model.DTOs.Brand.Brand;
import com.mobilele.model.DTOs.Model.GetModelDto;
import com.mobilele.model.entity.Brands;
import com.mobilele.model.entity.Offers;
import com.mobilele.repository.BrandRepository;
import com.mobilele.repository.ModelRepository;
import com.mobilele.repository.OfferRepository;
import com.mobilele.service.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;


    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper, ModelRepository modelRepository, OfferRepository offerRepository) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public List<Brand> getAllBrandsAndTheirModels() {
        return this.brandRepository.findAllWithModels().stream()
                .map(currentBrand-> new Brand(
                        currentBrand.getId(),
                        currentBrand.getName(),
                        currentBrand.getModels().stream()
                                .map(model -> new GetModelDto(
                                        model.getId(),
                                        model.getName(),
                                        model.getCategoryVehicle(),
                                        model.getStartYear(),
                                        model.getEndYear(),
                                        model.getImage()))
                                .sorted(Comparator.comparing(GetModelDto::getName))
                                .collect(Collectors.toList())
                )).sorted(Comparator.comparing(Brand::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<Brand> getAllBrands() {
       return this.brandRepository.findAll().stream().map(currentBrand -> new Brand(currentBrand.getId(), currentBrand.getName(), null)).toList();
    }

    @Override
    public Brand getCurrentBrand(Long id) {
        return modelMapper.map(this.brandRepository.findBrandAndModelsById(id), Brand.class);
    }


    @Override
    public void addNewBrand(AddNewBrand brand) {
        Brands mappedBrand = modelMapper.map(brand, Brands.class);
        this.brandRepository.save(mappedBrand);
    }

    @Override
    public void deleteBrand(Long id) {
            this.offerRepository.deleteAllByModel_Brand_Id(id);
            this.modelRepository.deleteAllByBrand_Id(id);
            this.brandRepository.deleteById(id);
    }
}

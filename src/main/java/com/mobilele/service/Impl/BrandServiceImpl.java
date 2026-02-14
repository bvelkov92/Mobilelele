package com.mobilele.service.Impl;

import com.mobilele.model.DTOs.BrandDto;
import com.mobilele.model.DTOs.ModelDTO;
import com.mobilele.repository.BrandRepository;
import com.mobilele.repository.ModelRepository;
import com.mobilele.service.BrandService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Setter
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    public BrandServiceImpl(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<BrandDto> getAllModelsFromBrand() {
        return this.brandRepository.findAll().stream()
                .map(currentBrand-> new BrandDto(
                        currentBrand.getName(),
                        modelRepository.findAllByBrandsId(currentBrand.getId()).stream()
                                .map(model -> new ModelDTO(model.getId(), model.getName()))
                                .sorted(Comparator.comparing(ModelDTO::getName))
                                .collect(Collectors.toList())
                )).sorted(Comparator.comparing(BrandDto::getBrand))
                .collect(Collectors.toList());

    }
}

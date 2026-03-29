package com.mobilele.model.DTOs.Offer;

import com.mobilele.model.entity.Brands;
import com.mobilele.model.entity.Models;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CurrentOfferDetails {

    @NotNull
    private Long id;

    @NotNull
    @Min(0)
    private Integer mileage;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal price;

    @NotBlank
    private String engineType;

    @NotBlank
    private String transmissionType;

    @NotNull
    private LocalDate offerCreated;

    @NotNull
    private LocalDate offerModified;

    @NotNull
    private Long modelId;

    @NotBlank
    private String user;

    private String imageUrl;

    private Brands brand;
    private Models model;
}

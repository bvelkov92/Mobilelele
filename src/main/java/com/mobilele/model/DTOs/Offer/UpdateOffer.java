package com.mobilele.model.DTOs.Offer;

import com.mobilele.model.entity.Brands;
import com.mobilele.model.enums.EngineTypeEnum;
import com.mobilele.model.enums.TransmissionTypeEnum;
import com.mobilele.model.enums.TypeOfVehicleEnums;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.servlet.tags.form.SelectTag;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
public class UpdateOffer {

    @NotNull
    private Long modelId;

    @NotNull
    private Long id;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal price;

    @NotNull
    private EngineTypeEnum engine;

    @NotNull
    private TransmissionTypeEnum transmissionType;

    @NotNull
    @Min(1800)
    private Integer year;

    @Positive
    @NotNull
    private Integer mileage;

    @Size(min = 10, max = 512)
    @NotBlank
    private String description;

    private String imageUrl;

//    @Query("SELECT DISTINCT name FROM Brands name ")
    private Brands brand;

}

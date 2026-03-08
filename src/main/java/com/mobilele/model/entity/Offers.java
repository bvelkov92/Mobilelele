package com.mobilele.model.entity;

import com.mobilele.model.enums.EngineTypeEnum;
import com.mobilele.model.enums.TransmissionTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "offer")
@Getter
@Setter
@NoArgsConstructor
public class Offers extends BaseEntity{

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private EngineTypeEnum engine;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private int mileage;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private TransmissionTypeEnum transmission;

    @Column(nullable = false)
    private int year;

    @ManyToOne
    private Models model;

    @ManyToOne
    private Users seller;

}

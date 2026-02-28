package com.mobilele.model.entity;

import com.mobilele.model.enums.EngineTypeEnum;
import com.mobilele.model.enums.TransmissionTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "offer")
@Getter
@Setter
@NoArgsConstructor
public class Offers extends BaseEntity{

    @Column(nullable = false)
    @NotNull
    @Size(min = 20)
    private String description;

    @Column(nullable = false)
    @NotNull
    private EngineTypeEnum engine;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    @NotNull
    private int mileage;

    @Column(nullable = false)
    @NotNull
    @Positive
    private double price;

    @Column(nullable = false)
    @NotNull
    private TransmissionTypeEnum transmission;

    @Column(nullable = false)
    @Positive
    @NotNull
    private int year;

    @ManyToOne
    private Models model;

    @ManyToOne
    private Users seller;

}

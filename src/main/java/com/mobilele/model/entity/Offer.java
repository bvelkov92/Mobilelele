package com.mobilele.model.entity;

import com.mobilele.model.enums.EngineTypeEnum;
import com.mobilele.model.enums.TransmissionTypeEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "offer")
@Getter
@Setter
public class Offer extends BaseEntity{

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
    private int miliage;

    @Column(nullable = false)
    @NotNull
    @Min(0)
    private double price;

    @Column(nullable = false)
    @NotNull
    private TransmissionTypeEnum transmission;

    @Column(nullable = false)
    @Min(0)
    @NotNull
    private int year;

    @Column
    private Date createdDate;

    @Column
    private Date modifiedDate;

    @OneToOne
    private Model model;

    @ManyToOne
    private User seller;

}

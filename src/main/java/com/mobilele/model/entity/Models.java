package com.mobilele.model.entity;

import com.mobilele.model.enums.TypeOfVehicleEnums;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "models")
@Getter
@Setter
public class Models extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_vehicle")
    private TypeOfVehicleEnums categoryVehicle;

    @Column(name = "start_year")
    @Positive
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column
    private String image;


    @ManyToOne
    @JoinColumn(name = "brands_id")
    private Brands brand;


}

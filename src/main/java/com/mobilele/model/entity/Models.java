package com.mobilele.model.entity;

import com.mobilele.model.enums.TypeOfVehicleEnums;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "models",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "brands_id"}))
@Getter
@Setter
public class Models extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_vehicle")
    private TypeOfVehicleEnums categoryVehicle;

    @Column(name = "start_year")
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column
    private String image;


    @ManyToOne
    @JoinColumn(name = "brands_id")
    private Brands brand;


}

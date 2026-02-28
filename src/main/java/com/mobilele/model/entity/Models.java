package com.mobilele.model.entity;

import com.mobilele.model.enums.TypeOfVehicleEnums;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "brands_id")
    private Brands brand;


}

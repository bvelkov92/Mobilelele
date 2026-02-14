package com.mobilele.model.entity;

import com.mobilele.model.enums.TypeOfVehicleEnums;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "model")
@Getter
@Setter
public class Model extends BaseEntity{

    @Column(nullable = false)
    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private TypeOfVehicleEnums category;

    @Column
    @Size(min = 6, max = 512)
    private String imageUrl;

    @Column
    private int startYear;

    @Column
    private int endYear;

    @Column (nullable = false)
    private Date created;

    @Column
    @NotNull
    private Date modified;

    @ManyToOne
    @JoinColumn(name = "brands_id")
    private Brand brands;


}

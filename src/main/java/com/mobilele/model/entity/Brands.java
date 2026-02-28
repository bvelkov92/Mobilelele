package com.mobilele.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "brands")
@Getter
@Setter
public class Brands extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "start_year")
    @Positive
    private Integer startYear;

    @Column(name = "end_year")
    private Integer endYear;

    @Column
    private String image;

    @OneToMany(mappedBy = "brand")
    private List<Models> models;
}

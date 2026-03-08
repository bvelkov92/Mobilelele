package com.mobilele.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @OneToMany(mappedBy = "brand")
    private List<Models> models;
}

package com.mobilele.model.DTOs.Brand;

import com.mobilele.model.DTOs.Model.Model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    private String brand;
    private List<Model>  models;

}

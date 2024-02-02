package org.mwdziak.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="nutrient")
public class Nutrient {
    @Id
    private Long id;
    private String name;
    private String unitName;
    @OneToMany(mappedBy = "nutrient")
    private List<FoodToNutrient> foodToNutrients;
}

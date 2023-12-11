package org.mwdziak.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="food_nutrient")
public class FoodToNutrient {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name="fdc_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name="nutrient_id")
    private Nutrient nutrient;

    private Double amount;


}

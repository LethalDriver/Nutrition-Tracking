package org.mwdziak.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ingredient {
    @Id
    private Integer fdcId;
    private String foodKind;
    private String description;
    private Double weight;
    private Double calories;
    private Double protein;
    private Double carbohydrates;
    private Double fat;
    @ManyToOne
    @JoinColumn(name="meal_id")
    private Meal meal;
}

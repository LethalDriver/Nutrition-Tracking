package org.mwdziak.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Nutrients {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name="ingredient_id")
    private Ingredient ingredient;
    private Double calories;
    private Double protein;
    private Double carbohydrates;
    private Double fat;
}
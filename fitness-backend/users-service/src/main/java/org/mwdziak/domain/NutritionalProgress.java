package org.mwdziak.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NutritionalProgress {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne()
    private Day day;
    private Double calories;
    private Double protein;
    private Double carbohydrates;
    private Double fat;

    public NutritionalProgress(Day day, double calories, double protein, double carbohydrates, double fat) {
        this.day = day;
        this.calories = calories;
        this.protein = protein;
        this.carbohydrates = carbohydrates;
        this.fat = fat;
    }
}
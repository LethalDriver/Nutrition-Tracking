package org.mwdziak.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class FoodNutrientDTO {
    private double calories;
    private double protein;
    private double fat;
    private double carbohydrates;
}

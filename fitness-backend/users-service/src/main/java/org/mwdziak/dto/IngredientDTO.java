package org.mwdziak.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IngredientDTO {
    private Integer fdcId;
    private String foodKind;
    private String description;
    private Double weight;
    private Double calories;
    private Double protein;
    private Double carbohydrates;
    private Double fat;
}
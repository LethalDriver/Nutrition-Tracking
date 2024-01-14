package org.mwdziak.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NutritionalGoalsDTO {
    private Double calories;
    private Double protein;
    private Double carbohydrates;
    private Double fat;
}

package org.mwdziak.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NutritionalGoalsDTO {
    private Double calories;
    private Double protein;
    private Double carbohydrates;
    private Double fat;
}

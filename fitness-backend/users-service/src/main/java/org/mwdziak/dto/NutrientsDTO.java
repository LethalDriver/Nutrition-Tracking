package org.mwdziak.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NutrientsDTO {
    private Double calories;
    private Double protein;
    private Double carbohydrates;
    private Double fat;
}

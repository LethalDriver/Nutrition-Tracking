package org.mwdziak.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NutritionalProgressDTO {
    private Double calories;
    private Double protein;
    private Double carbohydrates;
    private Double fat;
}
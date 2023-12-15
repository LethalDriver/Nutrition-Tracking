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
    private Long id;
    private String name;
    private String unitName;
    private Double quantity;
}

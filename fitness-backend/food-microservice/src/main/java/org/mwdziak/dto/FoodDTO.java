package org.mwdziak.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class FoodDTO {
    private String description;
    private List<FoodNutrientDTO> nutrients;
}

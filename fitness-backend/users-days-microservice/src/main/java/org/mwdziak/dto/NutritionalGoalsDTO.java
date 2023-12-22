package org.mwdziak.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NutritionalGoalsDTO {
    private Double carbohydratesGoal;
    private Double caloriesGoal;
    private Double proteinGoal;
    private Double fatGoal;
}

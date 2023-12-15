package org.mwdziak.service;

import lombok.AllArgsConstructor;
import org.mwdziak.domain.Meal;
import org.mwdziak.dto.MealDTO;
import org.springframework.stereotype.Service;

@Service
public class MealService {
    public MealDTO MealToMealDto(Meal meal) {
        return MealDTO.builder()
                .name(meal.getName())
                .calories(meal.getCalories())
                .protein(meal.getProtein())
                .fat(meal.getFat())
                .build();
    }
}

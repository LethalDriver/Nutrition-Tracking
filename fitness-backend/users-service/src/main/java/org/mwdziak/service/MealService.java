package org.mwdziak.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mwdziak.domain.Day;
import org.mwdziak.domain.Meal;
import org.mwdziak.dto.MealDTO;
import org.mwdziak.repository.DayRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MealService {
    public MealDTO MealToMealDto(Meal meal) {
        return MealDTO.builder()
                .name(meal.getName())
                .calories(meal.getCalories())
                .protein(meal.getProtein())
                .fat(meal.getFat())
                .build();
    }

    public Meal MealDtoToMeal(MealDTO mealDto) {
        return Meal.builder()
                .name(mealDto.getName())
                .calories(mealDto.getCalories())
                .protein(mealDto.getProtein())
                .carbohydrates(mealDto.getCarbohydrates())
                .fat(mealDto.getFat())
                .build();
    }

    public Meal addMealToDay(Meal meal, Day day) {
        return meal;
    }
}

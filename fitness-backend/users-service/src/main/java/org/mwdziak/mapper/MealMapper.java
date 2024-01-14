package org.mwdziak.mapper;

import org.mapstruct.Mapper;
import org.mwdziak.domain.Meal;
import org.mwdziak.dto.MealDTO;

@Mapper(uses = {IngredientMapper.class})
public interface MealMapper {
    MealDTO MealToMealDto(Meal meal);
    Meal MealDtoToMeal(MealDTO mealDto);
}

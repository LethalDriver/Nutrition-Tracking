package org.mwdziak.mapper;

import org.mapstruct.*;
import org.mwdziak.domain.Ingredient;
import org.mwdziak.domain.Meal;
import org.mwdziak.domain.Nutrients;
import org.mwdziak.dto.IngredientDTO;
import org.mwdziak.dto.MealDTO;
import org.mwdziak.dto.NutrientsDTO;

import java.util.List;
import java.util.Optional;

@Mapper(builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface MealMapper {
    MealDTO toDto(Meal meal);
    @Mapping(target = "ingredients", qualifiedByName = "ingredientDtoListToIngredientList")
    Meal toEntity(MealDTO mealDto);

    @IterableMapping(qualifiedByName = "ingredientDtoToIngredient")
    @Named("ingredientDtoListToIngredientList")
    List<Ingredient> ingredientDtoListToIngredientList(List<IngredientDTO> ingredientDTOList);

    @Named("ingredientDtoToIngredient")
    @Mapping(target = "nutrients", qualifiedByName = "nutrientsDtoToNutrients")
    Ingredient ingredientDtoToIngredient(IngredientDTO ingredient);
    @Named("nutrientsDtoToNutrients")
    Nutrients nutrientsDtoToNutrients(NutrientsDTO nutrientsDto);

    @AfterMapping
    default void setMeal(@MappingTarget Meal meal) {
        Optional.ofNullable(meal.getIngredients())
                .ifPresent(it -> it.forEach(item -> item.setMeal(meal)));
    }

    @AfterMapping
    default void setNutrients(@MappingTarget Ingredient ingredient) {
        Optional.ofNullable(ingredient.getNutrients())
                .ifPresent(it -> it.setIngredient(ingredient));
    }
}

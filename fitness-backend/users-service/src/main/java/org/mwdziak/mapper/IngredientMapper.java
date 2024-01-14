package org.mwdziak.mapper;

import org.mapstruct.Mapper;
import org.mwdziak.domain.Ingredient;
import org.mwdziak.dto.IngredientDTO;

@Mapper
public interface IngredientMapper {
    IngredientDTO IngredientToIngredientDto(Ingredient ingredient);
    Ingredient IngredientDtoToIngredient(IngredientDTO ingredientDto);
}

package org.mwdziak.services;

import lombok.AllArgsConstructor;
import org.mwdziak.domain.Food;
import org.mwdziak.domain.FoodToNutrient;
import org.mwdziak.dto.FoodDTO;
import org.mwdziak.dto.FoodNutrientDTO;
import org.mwdziak.repositories.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FoodService {
    private FoodRepository foodRepository;

    public List<FoodDTO> getFoodToSend(String foodName){
        List<Food> foods = foodRepository.findByDescriptionStartsWith(foodName);
        return foods.stream().map(this::foodToFoodDTO).collect(Collectors.toList());
    }

    private FoodDTO foodToFoodDTO(Food food){
        return FoodDTO.builder()
                .fdcId(food.getFdcId())
                .description(food.getDescription())
                .nutrients(food.getFoodToNutrients().stream().map(this::nutrientToFoodNutrientDTO)
                        .collect(Collectors.toList()))
                .build();

    }

    private FoodNutrientDTO nutrientToFoodNutrientDTO(FoodToNutrient foodToNutrient){
        return FoodNutrientDTO.builder()
                .id(foodToNutrient.getId())
                .name(foodToNutrient.getNutrient().getName())
                .quantity(foodToNutrient.getAmount())
                .unitName(foodToNutrient.getNutrient().getUnitName())
                .build();
    }
}

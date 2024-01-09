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

    public List<FoodDTO> getFoodToSend(String foodKind){
        List<Food> foods = foodRepository.findByFoodKind(foodKind);
        return foods.stream().map(this::foodToFoodDTO).collect(Collectors.toList());
    }

    private FoodDTO foodToFoodDTO(Food food){
        var foodDto = FoodDTO.builder()
                .fdcId(food.getFdcId())
                .description(food.getDescription())
                .foodKind(food.getFoodKind())
                .build();

        for (FoodToNutrient foodToNutrient : food.getFoodToNutrients()){
            var name = foodToNutrient.getNutrient().getName();
            if (name.equals("Protein")){
                foodDto.getNutrients().setProtein(foodToNutrient.getAmount());

            } else if (name.equals("Total lipid (fat)")) {
                foodDto.getNutrients().setFat(foodToNutrient.getAmount());

            } else if (name.equals("Carbohydrate, by difference")) {
                foodDto.getNutrients().setCarbohydrates(foodToNutrient.getAmount());

            } else if (name.equals("Energy")) {
                foodDto.getNutrients().setCalories(foodToNutrient.getAmount());

            }
        }

        return foodDto;


    }
}

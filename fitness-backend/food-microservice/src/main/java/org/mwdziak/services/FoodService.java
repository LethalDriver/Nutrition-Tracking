package org.mwdziak.services;

import lombok.AllArgsConstructor;
import org.mwdziak.domain.Food;
import org.mwdziak.domain.FoodToNutrient;
import org.mwdziak.domain.Nutrient;
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

        foodDto.setNutrients(getOptimalNutritionalValues(food));

        return foodDto;
    }

    private static FoodNutrientDTO getOptimalNutritionalValues(Food food) {
        var nutrientsDTO = new FoodNutrientDTO();

        var nutrients = food.getFoodToNutrients().stream().map(FoodToNutrient::getNutrient).toList();
        var nutrientNames = nutrients.stream().map(Nutrient::getName).toList();

        nutrientsDTO.setCalories(getNutrientAmount(food, nutrientNames, "Energy (Atwater General Factors)", "Energy (Atwater Specific Factors)", "Energy"));
        nutrientsDTO.setFat(getNutrientAmount(food, nutrientNames, "Total fat (NLEA)", "Total lipid (fat)"));
        nutrientsDTO.setCarbohydrates(getNutrientAmount(food, nutrientNames, "Carbohydrate, by difference"));
        nutrientsDTO.setProtein(getNutrientAmount(food, nutrientNames, "Protein"));

        return nutrientsDTO;
    }

    private static double getNutrientAmount(Food food, List<String> nutrientNames, String... nutrientOptions) {
        for (String nutrientOption : nutrientOptions) {
            if (nutrientNames.contains(nutrientOption)) {
                return food.getFoodToNutrients().stream()
                        .filter(foodToNutrient -> foodToNutrient.getNutrient().getName().equals(nutrientOption))
                        .findFirst()
                        .map(FoodToNutrient::getAmount)
                        .orElse(0.0);
            }
        }
        return 0.0;
    }
}

import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mwdziak.domain.Ingredient;
import org.mwdziak.domain.Meal;
import org.mwdziak.domain.Nutrients;
import org.mwdziak.domain.NutritionalGoals;
import org.mwdziak.dto.IngredientDTO;
import org.mwdziak.dto.MealDTO;
import org.mwdziak.dto.NutrientsDTO;
import org.mwdziak.dto.NutritionalGoalsDTO;
import org.mwdziak.mapper.MealMapper;
import org.mwdziak.mapper.NutritionalGoalsMapper;


import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class NutritionalMapperIntegrationTest {

    @Test
    public void givenSourceToDestination_whenMaps_thenCorrect() {
        NutritionalGoalsDTO nutritionalGoalsDTO = new NutritionalGoalsDTO();
        nutritionalGoalsDTO.setCalories(1.0);
        nutritionalGoalsDTO.setProtein(2.0);
        nutritionalGoalsDTO.setCarbohydrates(3.0);
        nutritionalGoalsDTO.setFat(4.0);

        NutritionalGoalsMapper mapper = Mappers.getMapper(NutritionalGoalsMapper.class);
        NutritionalGoals nutritionalGoals = mapper.NutritionalGoalsDtoToNutritionalGoals(nutritionalGoalsDTO);
        assertEquals(nutritionalGoalsDTO.getCalories(), nutritionalGoals.getCalories());
        assertEquals(nutritionalGoalsDTO.getProtein(), nutritionalGoals.getProtein());
        assertEquals(nutritionalGoalsDTO.getCarbohydrates(), nutritionalGoals.getCarbohydrates());
        assertEquals(nutritionalGoalsDTO.getFat(), nutritionalGoals.getFat());
    }


    @Test
    public void givenMealToMealDto_whenMaps_thenCorrect() {
        Meal meal = getMeal();

        MealMapper mapper = Mappers.getMapper(MealMapper.class);
        MealDTO mealDTO = mapper.MealToMealDto(meal);
        assertEquals(meal.getName(), mealDTO.getName());
        assertEquals(meal.getIngredients().get(0).getFoodKind(), mealDTO.getIngredients().get(0).getFoodKind());
        assertEquals(meal.getIngredients().get(0).getDescription(), mealDTO.getIngredients().get(0).getDescription());
        assertEquals(meal.getIngredients().get(0).getNutrients().getCalories(), mealDTO.getIngredients().get(0).getNutrients().getCalories());
        assertEquals(meal.getIngredients().get(0).getNutrients().getProtein(), mealDTO.getIngredients().get(0).getNutrients().getProtein());
        assertEquals(meal.getIngredients().get(0).getNutrients().getCarbohydrates(), mealDTO.getIngredients().get(0).getNutrients().getCarbohydrates());
        assertEquals(meal.getIngredients().get(0).getNutrients().getFat(), mealDTO.getIngredients().get(0).getNutrients().getFat());

    }

    private static Meal getMeal() {
        Nutrients nutrients = new Nutrients();
        nutrients.setCalories(1.0);
        nutrients.setProtein(2.0);
        nutrients.setCarbohydrates(3.0);
        nutrients.setFat(4.0);

        Ingredient ingredient = new Ingredient();
        ingredient.setFoodKind("foodKind");
        ingredient.setDescription("description");
        ingredient.setNutrients(nutrients);

        var ingredients = new ArrayList<Ingredient>();
        ingredients.add(ingredient);

        Meal meal = new Meal();
        meal.setName("name");
        meal.setIngredients(ingredients);
        return meal;
    }
}

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mwdziak.UsersDaysApplication;
import org.mwdziak.domain.*;
import org.mwdziak.dto.DayDTO;
import org.mwdziak.dto.MealDTO;
import org.mwdziak.dto.NutritionalGoalsDTO;
import org.mwdziak.mapper.DayMapper;
import org.mwdziak.mapper.MealMapper;
import org.mwdziak.mapper.NutritionalGoalsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UsersDaysApplication.class)
public class NutritionalMapperIntegrationTest {

    @Autowired
    private NutritionalGoalsMapper nutritionalGoalsMapper;

    @Autowired
    private MealMapper mealMapper;

    @Autowired
    private DayMapper dayMapper;

    @Test
    public void givenNutritonalGoalsToNutritionalGoalsDto_whenMaps_thenCorrect() {
        NutritionalGoalsDTO nutritionalGoalsDTO = new NutritionalGoalsDTO();
        nutritionalGoalsDTO.setCalories(1.0);
        nutritionalGoalsDTO.setProtein(2.0);
        nutritionalGoalsDTO.setCarbohydrates(3.0);
        nutritionalGoalsDTO.setFat(4.0);

        NutritionalGoals nutritionalGoals = nutritionalGoalsMapper.toEntity(nutritionalGoalsDTO);
        assertEquals(nutritionalGoalsDTO.getCalories(), nutritionalGoals.getCalories());
        assertEquals(nutritionalGoalsDTO.getProtein(), nutritionalGoals.getProtein());
        assertEquals(nutritionalGoalsDTO.getCarbohydrates(), nutritionalGoals.getCarbohydrates());
        assertEquals(nutritionalGoalsDTO.getFat(), nutritionalGoals.getFat());
    }


    @Test
    public void givenMealToMealDto_whenMaps_thenCorrect() {
        Meal meal = getMeal();


        MealDTO mealDTO = mealMapper.toDto(meal);
        assertEquals(meal.getName(), mealDTO.getName());
        assertEquals(meal.getIngredients().get(0).getFoodKind(), mealDTO.getIngredients().get(0).getFoodKind());
        assertEquals(meal.getIngredients().get(0).getDescription(), mealDTO.getIngredients().get(0).getDescription());
        assertEquals(meal.getIngredients().get(0).getNutrients().getCalories(), mealDTO.getIngredients().get(0).getNutrients().getCalories());
        assertEquals(meal.getIngredients().get(0).getNutrients().getProtein(), mealDTO.getIngredients().get(0).getNutrients().getProtein());
        assertEquals(meal.getIngredients().get(0).getNutrients().getCarbohydrates(), mealDTO.getIngredients().get(0).getNutrients().getCarbohydrates());
        assertEquals(meal.getIngredients().get(0).getNutrients().getFat(), mealDTO.getIngredients().get(0).getNutrients().getFat());
        assertEquals(meal.getIngredients().get(0).getWeight(), mealDTO.getIngredients().get(0).getWeight());
        assertEquals(meal.getIngredients().get(0).getFdcId(), mealDTO.getIngredients().get(0).getFdcId());

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
        ingredient.setWeight(1.0);
        ingredient.setFdcId(1);

        var ingredients = new ArrayList<Ingredient>();
        ingredients.add(ingredient);

        Meal meal = new Meal();
        meal.setName("name");
        meal.setIngredients(ingredients);
        return meal;
    }

    public void givenDayToDayDto_whenMaps_thenCorrect() {
        Day day = getDay();

        DayDTO dayDTO = dayMapper.toDto(day);
        assertEquals(day.getDate(), dayDTO.getDate());
        assertEquals(day.getMeals().size(), dayDTO.getMeals().size());
        assertEquals(day.getMeals().get(0).getName(), dayDTO.getMeals().get(0).getName());
    }

    private static Day getDay() {
        Meal meal = getMeal();

        List<Meal> meals = new ArrayList<>();
        meals.add(meal);

        Day day = new Day();
        day.setDate(LocalDate.now());
        day.setMeals(meals);
        return day;
    }
}

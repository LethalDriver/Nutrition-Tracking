import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mwdziak.domain.NutritionalGoals;
import org.mwdziak.dto.NutritionalGoalsDTO;
import org.mwdziak.mapper.NutritionalGoalsMapper;


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
}

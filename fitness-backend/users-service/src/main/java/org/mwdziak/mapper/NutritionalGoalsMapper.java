package org.mwdziak.mapper;

import org.mapstruct.Mapper;
import org.mwdziak.domain.NutritionalGoals;
import org.mwdziak.dto.NutritionalGoalsDTO;

@Mapper
public interface NutritionalGoalsMapper {
    NutritionalGoalsDTO NutritionalGoalsToNutritionalGoalsDto(NutritionalGoals nutritionalGoals);
    NutritionalGoals NutritionalGoalsDtoToNutritionalGoals(NutritionalGoalsDTO nutritionalGoalsDto);
}

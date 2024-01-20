package org.mwdziak.mapper;

import org.mapstruct.Mapper;
import org.mwdziak.domain.NutritionalGoals;
import org.mwdziak.dto.NutritionalGoalsDTO;

@Mapper(componentModel = "spring")
public interface NutritionalGoalsMapper {
    NutritionalGoalsDTO toDto(NutritionalGoals nutritionalGoals);
    NutritionalGoals toEntity(NutritionalGoalsDTO nutritionalGoalsDto);
}

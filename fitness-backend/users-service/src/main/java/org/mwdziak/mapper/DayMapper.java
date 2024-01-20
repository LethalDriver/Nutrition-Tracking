package org.mwdziak.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mwdziak.domain.Day;
import org.mwdziak.dto.DayDTO;

@Mapper(uses = {MealMapper.class}, builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface DayMapper {
    DayDTO toDto(Day day);
    Day toEntity(DayDTO dayDto);
}

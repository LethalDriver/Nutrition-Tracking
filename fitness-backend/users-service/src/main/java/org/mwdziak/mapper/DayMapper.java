package org.mwdziak.mapper;

import org.mapstruct.Mapper;
import org.mwdziak.domain.Day;
import org.mwdziak.dto.DayDTO;

@Mapper(uses = {MealMapper.class})
public interface DayMapper {
    DayDTO DayToDayDto(Day day);
    Day DayDtoToDay(DayDTO dayDto);
}

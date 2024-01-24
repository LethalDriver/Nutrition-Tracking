package org.mwdziak.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mwdziak.domain.Day;
import org.mwdziak.dto.DayDTO;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Mapper(uses = {MealMapper.class}, builder = @Builder(disableBuilder = true), componentModel = "spring")
public interface DayMapper {

    @Mapping(source = "date", target = "date", qualifiedByName = "dateToString")
    DayDTO toDto(Day day);
    Day toEntity(DayDTO dayDto);

    @Named("dateToString")
    default String dateToString(LocalDate date) {
        return date != null ? date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null;
    }
}

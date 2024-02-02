package org.mwdziak.mapper;

import org.mapstruct.Mapper;
import org.mwdziak.domain.NutritionalProgress;
import org.mwdziak.dto.NutritionalProgressDTO;

@Mapper(componentModel = "spring")
public interface NutritionalProgressMapper {
    NutritionalProgressDTO toDto(NutritionalProgress nutritionalProgress);
    NutritionalProgress toEntity(NutritionalProgressDTO nutritionalProgressDto);
}

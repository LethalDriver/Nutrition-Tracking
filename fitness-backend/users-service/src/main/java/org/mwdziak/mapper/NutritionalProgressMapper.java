package org.mwdziak.mapper;

import org.mapstruct.Mapper;
import org.mwdziak.domain.NutritionalProgress;
import org.mwdziak.dto.NutritionalProgressDTO;

@Mapper
public interface NutritionalProgressMapper {
    NutritionalProgressDTO NutritionalProgressToNutritionalProgressDto(NutritionalProgress nutritionalProgress);
    NutritionalProgress NutritionalProgressDtoToNutritionalProgress(NutritionalProgressDTO nutritionalProgressDto);
}

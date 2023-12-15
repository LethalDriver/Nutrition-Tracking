package org.mwdziak.service;

import lombok.AllArgsConstructor;
import org.mwdziak.domain.Day;
import org.mwdziak.dto.DayDTO;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DayService {
    MealService mealService;
    public DayDTO DayToDayDto(Day day) {
        return DayDTO.builder()
                .date(day.getDate())
                .meals(
                        day.getMeals().stream()
                                .map(mealService::MealToMealDto)
                                .collect(Collectors.toList())
                )
                .build();
    }

}

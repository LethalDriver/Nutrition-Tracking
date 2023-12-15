package org.mwdziak.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mwdziak.domain.Day;
import org.mwdziak.dto.DayDTO;
import org.mwdziak.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayService {
    private final MealService mealService;
    private final UserRepository userRepository;
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

    public List<DayDTO> getUserDays(String email){
        var user = userRepository.findByEmail(email).orElseThrow();
        var days = user.getDays();
        return days.stream()
                .map(this::DayToDayDto)
                .collect(Collectors.toList());
    }

}

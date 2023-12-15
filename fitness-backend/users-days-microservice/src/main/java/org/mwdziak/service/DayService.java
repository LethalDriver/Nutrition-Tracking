package org.mwdziak.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mwdziak.domain.Day;
import org.mwdziak.dto.DayDTO;
import org.mwdziak.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public boolean isDayExists(String email, Date date) {
        var user = userRepository.findByEmail(email).orElseThrow();
        var days = user.getDays();
        return days.stream()
                .anyMatch(day -> day.getDate().equals(date));
    }

    public Day getDay(String email, Date date) {
        var user = userRepository.findByEmail(email).orElseThrow();
        var days = user.getDays();
        return days.stream()
                .filter(day -> day.getDate().equals(date))
                .findFirst()
                .orElseThrow();
    }

    public Day createDay(String email, Date date) {
        var user = userRepository.findByEmail(email).orElseThrow();
        var day = Day.builder()
                .date(date)
                .user(user)
                .build();
        user.getDays().add(day);
        userRepository.save(user);
        return day;
    }


}

package org.mwdziak.service;

import lombok.RequiredArgsConstructor;
import org.mwdziak.domain.Day;
import org.mwdziak.dto.DayDTO;
import org.mwdziak.dto.NutritionalGoalsDTO;
import org.mwdziak.dto.NutritionalProgressDTO;
import org.mwdziak.repository.DayRepository;
import org.mwdziak.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayService {
    private final MealService mealService;
    private final UserRepository userRepository;
    private final DayRepository dayRepository;
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

    public boolean isDayExists(String email, LocalDate date) {
        var user = userRepository.findByEmail(email).orElseThrow();
        var days = user.getDays();
        return days.stream()
                .anyMatch(day -> day.getDate().equals(date));
    }

    public Day getDay(String email, LocalDate date) {
        var user = userRepository.findByEmail(email).orElseThrow();
        var days = user.getDays();
        return days.stream()
                .filter(day -> day.getDate().equals(date))
                .findFirst()
                .orElseThrow();
    }

    public void createDay(String email, LocalDate date) {
        var user = userRepository.findByEmail(email).orElseThrow();
        var day = Day.builder()
                .date(date)
                .user(user)
                .build();
        user.getDays().add(day);
        userRepository.save(user);
    }

    public NutritionalGoalsDTO getNutritionalProgress(String email) {
        var user = userRepository.findByEmail(email).orElseThrow();
        var day = getDay(email, getCurrentDate());
        return NutritionalGoalsDTO.builder()
                .calories(day.getNutritionalProgress().getCalories())
                .protein(day.getNutritionalProgress().getProtein())
                .carbohydrates(day.getNutritionalProgress().getCarbohydrates())
                .fat(day.getNutritionalProgress().getFat())
                .build();
    }

    public LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }


    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public void updateNutritionalProgress(String currentUserEmail, NutritionalProgressDTO nutritionalProgressDTO) {
        var user = userRepository.findByEmail(currentUserEmail).orElseThrow();
        var day = getDay(currentUserEmail, getCurrentDate());
        day.getNutritionalProgress().setCalories(nutritionalProgressDTO.getCalories());
        day.getNutritionalProgress().setProtein(nutritionalProgressDTO.getProtein());
        day.getNutritionalProgress().setCarbohydrates(nutritionalProgressDTO.getCarbohydrates());
        day.getNutritionalProgress().setFat(nutritionalProgressDTO.getFat());
        dayRepository.save(day);
    }
}

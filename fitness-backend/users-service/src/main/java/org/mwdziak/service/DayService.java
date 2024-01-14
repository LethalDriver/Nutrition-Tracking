package org.mwdziak.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.mwdziak.domain.Day;
import org.mwdziak.domain.Meal;
import org.mwdziak.domain.NutritionalProgress;
import org.mwdziak.dto.DayDTO;
import org.mwdziak.dto.MealDTO;
import org.mwdziak.dto.NutritionalGoalsDTO;
import org.mwdziak.dto.NutritionalProgressDTO;
import org.mwdziak.mapper.DayMapper;
import org.mwdziak.mapper.MealMapper;
import org.mwdziak.mapper.NutritionalProgressMapper;
import org.mwdziak.repository.DayRepository;
import org.mwdziak.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayService {
    private final UserRepository userRepository;
    private final DayRepository dayRepository;
    public DayDTO DayToDayDto(Day day) {
        return Mappers.getMapper(DayMapper.class).DayToDayDto(day);
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

    public NutritionalProgressDTO getNutritionalProgress(String email) {
        var user = userRepository.findByEmail(email).orElseThrow();
        var day = getDay(email, getCurrentDate());
        return nutritionalProgressToNutritionalProgressDto(day.getNutritionalProgress());
    }

    public LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }


    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public void updateNutritionalProgress(String currentUserEmail, NutritionalProgressDTO nutritionalProgressDTO) {
        try {
            var day = getDay(currentUserEmail, getCurrentDate());
            var nutritionalProgressUpdate = nutritionalProgressDtoToNutritionalProgress(nutritionalProgressDTO);
            var currentNutritionalProgress = day.getNutritionalProgress();
            addNutritionalProgress(currentNutritionalProgress, nutritionalProgressUpdate);
            dayRepository.save(day);

        } catch (NoSuchElementException e) {
            createDay(currentUserEmail, getCurrentDate());
            var day = getDay(currentUserEmail, getCurrentDate());
            var nutritionalProgress = nutritionalProgressDtoToNutritionalProgress(nutritionalProgressDTO);
            nutritionalProgress.setDay(day);
            day.setNutritionalProgress(nutritionalProgress);
            dayRepository.save(day);
        }
    }

    private static void addNutritionalProgress(NutritionalProgress currentNutritionalProgress, NutritionalProgress nutritionalProgressUpdate) {
        currentNutritionalProgress.setCalories(currentNutritionalProgress.getCalories() + nutritionalProgressUpdate.getCalories());
        currentNutritionalProgress.setProtein(currentNutritionalProgress.getProtein() + nutritionalProgressUpdate.getProtein());
        currentNutritionalProgress.setCarbohydrates(currentNutritionalProgress.getCarbohydrates() + nutritionalProgressUpdate.getCarbohydrates());
        currentNutritionalProgress.setFat(currentNutritionalProgress.getFat() + nutritionalProgressUpdate.getFat());
    }

    public void addMealToDay(String currentUserEmail, Meal meal) {
        var day = getDay(currentUserEmail, getCurrentDate());
        day.getMeals().add(meal);
        dayRepository.save(day);
    }

    private NutritionalProgressDTO nutritionalProgressToNutritionalProgressDto(NutritionalProgress nutritionalProgress) {
        return Mappers.getMapper(NutritionalProgressMapper.class)
                .NutritionalProgressToNutritionalProgressDto(nutritionalProgress);
    }
    private NutritionalProgress nutritionalProgressDtoToNutritionalProgress(NutritionalProgressDTO nutritionalProgressDTO) {
        return Mappers.getMapper(NutritionalProgressMapper.class)
                .NutritionalProgressDtoToNutritionalProgress(nutritionalProgressDTO);
    }

    public MealDTO MealToMealDto(Meal meal) {
        return Mappers.getMapper(MealMapper.class).MealToMealDto(meal);
    }

    public Meal MealDtoToMeal(MealDTO mealDto) {
        return Mappers.getMapper(MealMapper.class).MealDtoToMeal(mealDto);
    }
}

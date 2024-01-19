package org.mwdziak.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.mwdziak.domain.Day;
import org.mwdziak.domain.Meal;
import org.mwdziak.domain.NutritionalProgress;
import org.mwdziak.dto.*;
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

    public Day getDay(String email, LocalDate date) {
        var user = userRepository.findByEmail(email).orElseThrow();
        var days = user.getDays();
        return days.stream()
                .filter(day -> day.getDate().equals(date))
                .findFirst()
                .orElseThrow();
    }

    public Day createDay(String email, LocalDate date) {
        var user = userRepository.findByEmail(email).orElseThrow();
        var day = new Day(date, user);
        user.getDays().add(day);
        userRepository.save(user);
        return day;
    }

    public NutritionalProgressDTO getNutritionalProgress(String email) {
        try {
            var day = getDay(email, getCurrentDate());
            return nutritionalProgressToNutritionalProgressDto(day.getNutritionalProgress());
        } catch (NoSuchElementException e) {
            var day = createDay(email, getCurrentDate());
            return nutritionalProgressToNutritionalProgressDto(day.getNutritionalProgress());
        }
    }

    public LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(date, formatter);
    }


    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    public void updateNutritionalProgress(String currentUserEmail, NutritionalProgressDTO nutritionalProgressDTO,
                                          Day day) {
        var currentNutritionalProgress = day.getNutritionalProgress();
        addNutritionalProgress(currentNutritionalProgress, nutritionalProgressDTO);
    }

    private Day createDayIfNotExists(String currentUserEmail) {
        try {
            return getDay(currentUserEmail, getCurrentDate());
        } catch (NoSuchElementException e) {
            return createDay(currentUserEmail, getCurrentDate());
        }
    }

    private static void addNutritionalProgress(NutritionalProgress currentNutritionalProgress, NutritionalProgressDTO nutritionalProgressUpdate) {
        currentNutritionalProgress.setCalories(currentNutritionalProgress.getCalories() + nutritionalProgressUpdate.getCalories());
        currentNutritionalProgress.setProtein(currentNutritionalProgress.getProtein() + nutritionalProgressUpdate.getProtein());
        currentNutritionalProgress.setCarbohydrates(currentNutritionalProgress.getCarbohydrates() + nutritionalProgressUpdate.getCarbohydrates());
        currentNutritionalProgress.setFat(currentNutritionalProgress.getFat() + nutritionalProgressUpdate.getFat());
    }

    public void addMealAndUpdateNutritionalProgressForDay(String currentUserEmail, MealDTO mealDTO) {
        var meal = MealDtoToMeal(mealDTO);
        var nutritionalProgressUpdate = getNutritionalUpdateFromMealDto(mealDTO);
        var day = createDayIfNotExists(currentUserEmail);
        updateNutritionalProgress(currentUserEmail, nutritionalProgressUpdate, day);
        meal.setDay(day);
        day.getMeals().add(meal);
        dayRepository.save(day);
    }

    public NutritionalProgressDTO getNutritionalUpdateFromMealDto(MealDTO mealDTO) {
        var nutritionalProgressDTO = new NutritionalProgressDTO();
        var Ingredients = mealDTO.getIngredients();
        nutritionalProgressDTO.setCalories(Ingredients.stream()
                .mapToDouble(IngredientDTO -> IngredientDTO.getNutrients().getCalories())
                .sum());
        nutritionalProgressDTO.setProtein(Ingredients.stream()
                .mapToDouble(IngredientDTO -> IngredientDTO.getNutrients().getProtein())
                .sum());
        nutritionalProgressDTO.setCarbohydrates(Ingredients.stream()
                .mapToDouble(IngredientDTO -> IngredientDTO.getNutrients().getCarbohydrates())
                .sum());
        nutritionalProgressDTO.setFat(Ingredients.stream()
                .mapToDouble(IngredientDTO -> IngredientDTO.getNutrients().getFat())
                .sum());
        return nutritionalProgressDTO;
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

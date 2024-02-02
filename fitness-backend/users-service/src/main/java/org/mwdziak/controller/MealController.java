package org.mwdziak.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.dto.MealDTO;
import org.mwdziak.service.DayService;
import org.mwdziak.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MealController {
    private final UserService userService;
    private final DayService dayService;

    @PostMapping("/user/day/meals")
    public void addMeal(@RequestBody MealDTO mealDTO) {
        dayService.addMealAndUpdateNutritionalProgressForDay(userService.getCurrentUserEmail(), mealDTO);
    }
}

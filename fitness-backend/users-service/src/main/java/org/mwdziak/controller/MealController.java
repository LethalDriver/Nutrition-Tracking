package org.mwdziak.controller;

import org.mwdziak.dto.MealDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MealController {

    @PostMapping("/user/day/meals")
    public void addMeal(@RequestBody MealDTO mealDTO) {

    }
}

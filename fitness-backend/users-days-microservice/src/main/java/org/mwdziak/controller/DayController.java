package org.mwdziak.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.domain.Day;
import org.mwdziak.dto.DayDTO;
import org.mwdziak.dto.NutritionalGoalsDTO;
import org.mwdziak.dto.NutritionalProgressDTO;
import org.mwdziak.service.DayService;
import org.mwdziak.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DayController {
    private final DayService dayService;
    private final UserService userService;
    @GetMapping("/user/day/exists")
    public boolean isDayCreated() {
        return dayService.isDayExists(userService.getCurrentUserEmail(),
                dayService.getCurrentDate());
    }

    @GetMapping("/user/day/progress")
    public NutritionalGoalsDTO getNutritionalProgress() {
        return dayService.getNutritionalProgress(userService.getCurrentUserEmail());
    }

    @PostMapping("/user/day/create")
    public void createNewDay() {
        dayService.createDay(userService.getCurrentUserEmail(),
                dayService.getCurrentDate());
    }

    @PutMapping("/user/day/progress")
    public void updateNutrients(@RequestBody NutritionalProgressDTO nutritionalProgressDTO) {
        dayService.updateNutritionalProgress(userService.getCurrentUserEmail(),
                nutritionalProgressDTO);
    }




}

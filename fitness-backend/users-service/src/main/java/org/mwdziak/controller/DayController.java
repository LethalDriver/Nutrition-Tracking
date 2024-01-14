package org.mwdziak.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.dto.NutritionalGoalsDTO;
import org.mwdziak.dto.NutritionalProgressDTO;
import org.mwdziak.service.DayService;
import org.mwdziak.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class DayController {
    private final DayService dayService;
    private final UserService userService;

    @GetMapping("/user/day/progress")
    public NutritionalProgressDTO getNutritionalProgress() {
        return dayService.getNutritionalProgress(userService.getCurrentUserEmail());
    }

    @PutMapping("/user/day/progress")
    public void updateNutrients(@RequestBody NutritionalProgressDTO nutritionalProgressDTO) {
        dayService.updateNutritionalProgress(userService.getCurrentUserEmail(),
                nutritionalProgressDTO);
    }

}

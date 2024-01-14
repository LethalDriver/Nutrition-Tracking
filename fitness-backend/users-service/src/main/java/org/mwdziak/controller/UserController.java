package org.mwdziak.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.dto.NutritionalGoalsDTO;
import org.mwdziak.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/user/goals/update")
    public HttpStatus updateUserNutrients(@RequestBody NutritionalGoalsDTO nutritionalGoalsDTO) {
        userService.updateUserNutritionalGoals(nutritionalGoalsDTO);
        return HttpStatus.OK;
    }

    @GetMapping("/user/goals/get")
    public NutritionalGoalsDTO getUserNutrients() {
        return userService.getUserNutritionalGoals();
    }
}

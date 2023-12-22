package org.mwdziak.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.dto.NutritionalGoalsDTO;
import org.mwdziak.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/user/update/nutrients")
    public HttpStatus updateUserNutrients(@RequestBody NutritionalGoalsDTO goals) {
        userService.updateUserNutrients(goals);
        return HttpStatus.OK;
    }
}

package org.mwdziak.controller;

import lombok.RequiredArgsConstructor;
import org.mwdziak.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/user/update/nutrients")
    public HttpStatus updateUserNutrients() {
        userService.updateUserNutrients();
        return HttpStatus.OK;
    }
}

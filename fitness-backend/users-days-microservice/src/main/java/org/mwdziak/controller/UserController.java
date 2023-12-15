package org.mwdziak.controller;

import lombok.AllArgsConstructor;
import org.mwdziak.domain.User;
import org.mwdziak.dto.CreateUserDTO;
import org.mwdziak.dto.LoginUserDTO;
import org.mwdziak.repository.UserRepository;
import org.mwdziak.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {

    UserService userService;
    UserRepository userRepository;

    @PostMapping("/register")
    public User registerUser(@RequestBody CreateUserDTO user) {
        return userRepository.save(userService.createUserDtoToUser(user));
    }

    @GetMapping("/days")
    public void getUserDays(@RequestParam String email) {
        // Logic to get the user's days
    }
}

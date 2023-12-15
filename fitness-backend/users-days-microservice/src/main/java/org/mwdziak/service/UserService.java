package org.mwdziak.service;

import org.mwdziak.domain.Day;
import org.mwdziak.domain.Meal;
import org.mwdziak.domain.User;
import org.mwdziak.dto.CreateUserDTO;
import org.mwdziak.dto.DayDTO;
import org.mwdziak.dto.MealDTO;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {
    public User createUserDtoToUser(CreateUserDTO createUserDTO) {
        return User.builder()
                .email(createUserDTO.getEmail())
                .password(createUserDTO.getPassword())
                .name(createUserDTO.getName())
                .hydrationGoal(createUserDTO.getHydrationGoal())
                .caloriesGoal(createUserDTO.getCaloriesGoal())
                .proteinGoal(createUserDTO.getProteinGoal())
                .fatGoal(createUserDTO.getFatGoal())
                .build();
    }

}

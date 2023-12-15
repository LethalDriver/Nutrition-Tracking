package org.mwdziak.service;

import org.mwdziak.domain.User;
import org.mwdziak.dto.CreateUserDTO;
import org.springframework.stereotype.Service;

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

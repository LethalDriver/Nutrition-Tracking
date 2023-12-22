package org.mwdziak.service;

import lombok.RequiredArgsConstructor;
import org.mwdziak.domain.NutritionalGoals;
import org.mwdziak.dto.NutritionalGoalsDTO;
import org.mwdziak.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void updateUserNutrients(NutritionalGoalsDTO goals) {
        String userEmail = getCurrentUserEmail();
        userRepository.findByEmail(userEmail).ifPresent(user -> {
            NutritionalGoals nutritionalGoals = nutrientDtoToNutrientGoals(goals);
            nutritionalGoals.setUser(user);
            user.setNutritionalGoals(nutritionalGoals);
            userRepository.save(user);
        });

    }

    public String getCurrentUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        }

        return null;
    }

    private NutritionalGoals nutrientDtoToNutrientGoals(NutritionalGoalsDTO goals) {
        return NutritionalGoals.builder()
                .calories(goals.getCaloriesGoal())
                .carbohydrates(goals.getCarbohydratesGoal())
                .fat(goals.getFatGoal())
                .protein(goals.getProteinGoal())
                .build();
    }
}

package org.mwdziak.service;

import lombok.RequiredArgsConstructor;
import org.mwdziak.domain.NutritionalGoals;
import org.mwdziak.domain.User;
import org.mwdziak.dto.NutritionalGoalsDTO;
import org.mwdziak.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void updateUserNutrients(NutritionalGoalsDTO nutritionalGoalsDTO) {
        String userEmail = getCurrentUserEmail();
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        NutritionalGoals nutritionalGoals = convertToEntity(nutritionalGoalsDTO);
        user.setNutritionalGoals(nutritionalGoals);
        userRepository.save(user);
    }

    public NutritionalGoalsDTO getUserNutrients() {
        String userEmail = getCurrentUserEmail();
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        NutritionalGoals nutritionalGoals = user.getNutritionalGoals();
        return convertToDTO(nutritionalGoals);
    }

    public String getCurrentUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        }

        return null;
    }

    private NutritionalGoalsDTO convertToDTO(NutritionalGoals nutritionalGoals) {
        return NutritionalGoalsDTO.builder()
                .calories(nutritionalGoals.getCalories())
                .protein(nutritionalGoals.getProtein())
                .carbohydrates(nutritionalGoals.getCarbohydrates())
                .fat(nutritionalGoals.getFat())
                .build();
    }

    private NutritionalGoals convertToEntity(NutritionalGoalsDTO nutritionalGoalsDTO) {
        return NutritionalGoals.builder()
                .calories(nutritionalGoalsDTO.getCalories())
                .protein(nutritionalGoalsDTO.getProtein())
                .carbohydrates(nutritionalGoalsDTO.getCarbohydrates())
                .fat(nutritionalGoalsDTO.getFat())
                .build();
    }
}

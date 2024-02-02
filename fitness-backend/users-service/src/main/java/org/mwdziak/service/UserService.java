package org.mwdziak.service;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.mwdziak.domain.NutritionalGoals;
import org.mwdziak.domain.User;
import org.mwdziak.dto.NutritionalGoalsDTO;
import org.mwdziak.mapper.NutritionalGoalsMapper;
import org.mwdziak.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public void updateUserNutritionalGoals(NutritionalGoalsDTO nutritionalGoalsDTO) {
        String userEmail = getCurrentUserEmail();
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        NutritionalGoals nutritionalGoals = nutritionalGoalsDtoToNutritionalGoals(nutritionalGoalsDTO);
        nutritionalGoals.setUser(user);
        user.setNutritionalGoals(nutritionalGoals);
        userRepository.save(user);
    }

    public NutritionalGoalsDTO getUserNutritionalGoals() {
        String userEmail = getCurrentUserEmail();
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        NutritionalGoals nutritionalGoals = user.getNutritionalGoals();
        return nutritionalGoalsToNutritionalGoalsDto(nutritionalGoals);
    }


    public String getCurrentUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        }

        return null;
    }

    private NutritionalGoalsDTO nutritionalGoalsToNutritionalGoalsDto(NutritionalGoals nutritionalGoals) {
        return Mappers.getMapper(NutritionalGoalsMapper.class)
                .toDto(nutritionalGoals);
    }

    private NutritionalGoals nutritionalGoalsDtoToNutritionalGoals(NutritionalGoalsDTO nutritionalGoalsDTO) {
        return Mappers.getMapper(NutritionalGoalsMapper.class)
                .toEntity(nutritionalGoalsDTO);
    }

}

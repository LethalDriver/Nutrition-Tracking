package org.mwdziak.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDTO {
    private String email;
    private String password;
    private String name;
    private Double hydrationGoal;
    private Double caloriesGoal;
    private Double proteinGoal;
    private Double fatGoal;
}

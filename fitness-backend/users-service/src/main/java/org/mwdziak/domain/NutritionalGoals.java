package org.mwdziak.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NutritionalGoals {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne()
    private User user;
    private Double calories;
    private Double protein;
    private Double carbohydrates;
    private Double fat;

}

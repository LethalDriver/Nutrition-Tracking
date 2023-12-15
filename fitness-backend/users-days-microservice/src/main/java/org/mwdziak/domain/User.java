package org.mwdziak.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Entity
@Table(name="_users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String name;
    private Double hydrationGoal;
    private Double caloriesGoal;
    private Double proteinGoal;
    private Double fatGoal;
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Day> days;

}

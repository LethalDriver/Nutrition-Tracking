package domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@Entity
@Table(name="_users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;
    private boolean areGoalsSet;
    private Double hydrationGoal;
    private Double caloriesGoal;
    private Double proteinGoal;
    private Double fatGoal;
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL)
    private List<Day> days;

}

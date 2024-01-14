package org.mwdziak.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="meals")
public class Meal {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;
    @ManyToOne
    @JoinColumn(name="day_id")
    private Day day;
}

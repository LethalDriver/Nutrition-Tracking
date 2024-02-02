package org.mwdziak.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer fdcId;
    private String foodKind;
    private String description;
    private Double weight;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="nutrients_id")
    private Nutrients nutrients;
    @ManyToOne()
    @JoinColumn(name="meal_id")
    private Meal meal;
}

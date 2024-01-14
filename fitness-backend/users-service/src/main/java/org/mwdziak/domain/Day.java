package org.mwdziak.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="days")
@Builder
public class Day {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @OneToMany(mappedBy="day", cascade=CascadeType.ALL)
    private List<Meal> meals;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nutritional_progress_id")
    private NutritionalProgress nutritionalProgress;


    public Day(LocalDate date, User user) {
        this.date = date;
        this.user = user;
        this.meals = new ArrayList<Meal>();
        this.nutritionalProgress = new NutritionalProgress(this, 0.0, 0.0, 0.0, 0.0);
    }
}

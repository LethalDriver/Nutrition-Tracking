package org.mwdziak.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name="food")
public class Food {
    @Id
    private Long fdcId;
    private String foodKind;
    private String description;
    private Integer foodCategoryId;
    @OneToMany(mappedBy = "food")
    private List<FoodToNutrient> foodToNutrients;

}

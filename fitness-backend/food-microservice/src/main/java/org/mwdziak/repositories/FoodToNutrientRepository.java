package org.mwdziak.repositories;

import org.mwdziak.domain.Food;
import org.mwdziak.domain.FoodToNutrient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodToNutrientRepository extends JpaRepository<FoodToNutrient, Long> {
}

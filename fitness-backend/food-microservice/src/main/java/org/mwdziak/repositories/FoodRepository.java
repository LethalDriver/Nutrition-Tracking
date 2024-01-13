package org.mwdziak.repositories;

import org.mwdziak.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    public List<Food> findByFoodKind(String foodKind);
    @Query("SELECT DISTINCT f.foodKind FROM Food f")
    List<String> findDistinctFoodKinds();
}

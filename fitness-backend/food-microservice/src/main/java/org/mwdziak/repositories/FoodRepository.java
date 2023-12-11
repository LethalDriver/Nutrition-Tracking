package org.mwdziak.repositories;

import org.mwdziak.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    public List<Food> findByDescriptionStartsWith(String foodName);
}

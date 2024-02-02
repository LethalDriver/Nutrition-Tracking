package org.mwdziak.repository;

import org.mwdziak.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long>{

}

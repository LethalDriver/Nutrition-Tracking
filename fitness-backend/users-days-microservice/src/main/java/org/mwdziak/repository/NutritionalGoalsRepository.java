package org.mwdziak.repository;

import org.mwdziak.domain.NutritionalGoals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NutritionalGoalsRepository extends JpaRepository<NutritionalGoals, Long> {
}

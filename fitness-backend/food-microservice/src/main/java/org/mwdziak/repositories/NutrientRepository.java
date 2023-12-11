package org.mwdziak.repositories;

import jakarta.annotation.Nonnull;
import org.mwdziak.domain.Nutrient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface NutrientRepository extends JpaRepository<Nutrient, Long> {
}

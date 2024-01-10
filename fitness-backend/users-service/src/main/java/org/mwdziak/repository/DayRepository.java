package org.mwdziak.repository;

import org.mwdziak.domain.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface DayRepository extends JpaRepository<Day, Long> {
}

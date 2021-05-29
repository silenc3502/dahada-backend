package com.dahada.backend.application.calculation.repository;

import com.dahada.backend.application.calculation.entity.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {
}

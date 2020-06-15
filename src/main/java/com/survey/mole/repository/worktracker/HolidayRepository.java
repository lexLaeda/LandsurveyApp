package com.survey.mole.repository.worktracker;

import com.survey.mole.model.worktracker.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}

package com.survey.mole.service.worktracker;

import com.survey.mole.model.worktracker.Holiday;

import java.util.List;

public interface HolidayService {

    Holiday save(Holiday holiday);

    Holiday update(Long id, Holiday holiday);

    Holiday findById(Long id);

    List<Holiday> findAll();

    Boolean delete(Holiday holiday);
}

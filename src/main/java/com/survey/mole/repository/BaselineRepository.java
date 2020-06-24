package com.survey.mole.repository;

import com.survey.mole.model.survey.Baseline;
import com.survey.mole.model.survey.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaselineRepository extends JpaRepository<Baseline, Long> {
        List<Baseline> findAllByPointsContains(Point point);
}

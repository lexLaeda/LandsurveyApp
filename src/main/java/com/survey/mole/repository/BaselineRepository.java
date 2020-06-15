package com.survey.mole.repository;

import com.survey.mole.model.survey.Baseline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaselineRepository extends JpaRepository<Baseline, Long> {

}

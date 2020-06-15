package com.survey.mole.service.worktracker;

import com.survey.mole.model.worktracker.Code;

import java.util.List;

public interface CodeService {

    Code save(Code code);

    Code update(Long id, Code code);

    Code findById(Long id);

    List<Code> findAll();

    Boolean delete(Code code);
}

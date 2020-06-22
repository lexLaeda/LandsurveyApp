package com.survey.mole.service.worktracker;

import com.survey.mole.exception.CodeNotFoundException;
import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.worktracker.Code;
import com.survey.mole.repository.worktracker.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {

    private CodeRepository repository;

    @Autowired
    public CodeServiceImpl(CodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Code save(Code code) {
        return repository.saveAndFlush(code);
    }

    @Override
    public Code update(Long id, Code code) {
        Code byId = findById(id);
        code.setId(id);
        return repository.saveAndFlush(code);
    }

    @Override
    public Code findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new ElementNotFoundException("Code with id " + id + " not found"));
    }

    @Override
    public List<Code> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean delete(Code code) {
        long before = repository.count();
        repository.delete(code);
        long after = repository.count();
        return before - after == 1;
    }
}

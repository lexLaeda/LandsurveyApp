package com.survey.mole.service.worktracker;

import com.survey.mole.exception.ElementNotFoundException;
import com.survey.mole.model.worktracker.Holiday;
import com.survey.mole.repository.worktracker.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {

    private HolidayRepository repository;

    @Autowired
    public HolidayServiceImpl(HolidayRepository repository) {
        this.repository = repository;
    }

    @Override
    public Holiday save(Holiday holiday) {
        return repository.saveAndFlush(holiday);
    }

    @Override
    public Holiday update(Long id, Holiday holiday) {
        Holiday holiday1 = findById(id);
        holiday.setId(id);
        return repository.saveAndFlush(holiday);
    }

    @Override
    public Holiday findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Holiday with id " + id + " not found!!!"));
    }

    @Override
    public List<Holiday> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean delete(Holiday holiday) {
        long before = repository.count();
        repository.delete(holiday);
        long after = repository.count();
        return after - before == 1;
    }
}

package com.survey.mole.controller;

import com.survey.mole.model.worktracker.Holiday;
import com.survey.mole.service.worktracker.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/holiday")
public class HolidayController {

    private HolidayService holidayService;

    @Autowired
    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }


    @GetMapping("/{id}")
    public Holiday findHolidayById(@PathVariable("id") Long id) {
        Holiday byId = holidayService.findById(id);
        return byId;
    }

    @GetMapping("/list")
    public List<Holiday> findAll() {
        return holidayService.findAll();
    }

    @PostMapping("/add")
    public Holiday addNewHoliday(@RequestBody Holiday holiday) {
        return holidayService.save(holiday);
    }

    @PostMapping("/edit/{id}")
    public Holiday editBaseline(@PathVariable("id") Long id, @RequestBody Holiday holiday) {
        return holidayService.update(id, holiday);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteBaseline(@PathVariable("id") Long id) {
        Holiday byId = holidayService.findById(id);
        return holidayService.delete(byId);
    }
}

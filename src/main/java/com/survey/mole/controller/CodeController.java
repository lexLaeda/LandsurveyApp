package com.survey.mole.controller;


import com.survey.mole.model.worktracker.Code;
import com.survey.mole.service.worktracker.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/code")
public class CodeController {

    private CodeService codeService;

    @Autowired
    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }


    @GetMapping("/{id}")
    public Code findCodeById(@PathVariable("id") Long id) {
        Code byId = codeService.findById(id);
        return byId;
    }

    @GetMapping("/list")
    public List<Code> findAll() {
        return codeService.findAll();
    }

    @PostMapping("/add")
    public Code addNewCode(@RequestBody Code code) {
        return codeService.save(code);
    }

    @PostMapping("/edit/{id}")
    public Code editCode(@PathVariable("id") Long id, @RequestBody Code code) {
        return codeService.update(id, code);
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteCode(@PathVariable("id") Long id) {
        Code byId = codeService.findById(id);
        return codeService.delete(byId);
    }
}

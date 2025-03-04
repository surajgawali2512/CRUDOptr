package com.demo.controller;

import com.demo.model.Institute;
import com.demo.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/institutes")
public class InstituteController {

    @Autowired
    private InstituteService instituteService;

    @PostMapping
    public Institute createInstitute(@RequestBody Institute institute) {
        return instituteService.createInstitute(institute);
    }
}

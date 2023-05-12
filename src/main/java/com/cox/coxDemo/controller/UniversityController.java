package com.cox.coxDemo.controller;

import com.cox.coxDemo.dto.EmployeeDto;
import com.cox.coxDemo.dto.UniversityDto;
import com.cox.coxDemo.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping("/getAll")
    public Flux<UniversityDto> getAllUniversities() {
        return universityService.getAllUniversities();
    }

    @PostMapping("/add")
    public Mono<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        return universityService.addEmployee(employeeDto);
    }

}

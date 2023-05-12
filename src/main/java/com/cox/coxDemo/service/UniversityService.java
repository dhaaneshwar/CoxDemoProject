package com.cox.coxDemo.service;

import com.cox.coxDemo.dto.EmployeeDto;
import com.cox.coxDemo.dto.UniversityDto;
import com.cox.coxDemo.entity.Employee;
import com.cox.coxDemo.entity.Universities;
import com.cox.coxDemo.exception.EmptyInputException;
import com.cox.coxDemo.exception.InvalidInputException;
import com.cox.coxDemo.exception.NoValueFoundException;
import com.cox.coxDemo.mapping.EmployeeMapper;
import com.cox.coxDemo.repository.UniversityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class UniversityService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UniversityRepository universityRepository;

    private static final Logger logger = LoggerFactory.getLogger(UniversityService.class);

    public Flux<UniversityDto> getAllUniversities() {

        UniversityDto[] listOfUniversity= restTemplate.getForObject("http://universities.hipolabs.com/search?country=United+States", UniversityDto[].class);
        if(listOfUniversity.equals(null)) {
            throw new NoValueFoundException("No values found");
        }
        List<UniversityDto> myList= Arrays.asList(listOfUniversity);
        return Flux.fromIterable(myList);
    }

    public Mono<EmployeeDto> addEmployee(EmployeeDto employeeDto) {

        Employee employee= EmployeeMapper.mapToEmployee(employeeDto);

        if(employee.getName().isEmpty() || employee.getName().length()==0 || employee.getAge()==null) {
            throw new EmptyInputException("601","Input fields are empty");
        }

        if(employee.getAge()>100 || employee.getAge()<=0) {
            throw new InvalidInputException("601","Input invalid, Please provide valid age");
        }

        Mono<Employee> saveEmployee= universityRepository.save(employee);

        return saveEmployee.map(e->EmployeeMapper.mapToEmployeeDto(e));
    }



}

package com.cox.coxDemo.mapping;

import com.cox.coxDemo.dto.EmployeeDto;
import com.cox.coxDemo.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getName(),
                employee.getAge()
        );
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getName(),
                employeeDto.getAge()
        );
    }

}

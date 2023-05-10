package com.cox.coxDemo.repository;

import com.cox.coxDemo.entity.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UniversityRepository extends ReactiveMongoRepository<Employee,String> {

}

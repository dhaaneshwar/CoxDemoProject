package com.cox.coxDemo;

import com.cox.coxDemo.controller.UniversityController;
import com.cox.coxDemo.dto.EmployeeDto;
import com.cox.coxDemo.dto.UniversityDto;
import com.cox.coxDemo.service.UniversityService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebFluxTest(UniversityController.class)
class CoxDemoApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UniversityService universityService;

    @Test
    public void addEmployeeTest(){
        EmployeeDto employeeDto=new EmployeeDto("101","dhaanesh",22);

        when(universityService.addEmployee(employeeDto)).thenReturn(Mono.just(employeeDto));

        webTestClient.post().uri("/add")
                .body(Mono.just(employeeDto),EmployeeDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(System.out::println)
                .jsonPath("$.name").isEqualTo(employeeDto.getName())
                .jsonPath("$.age").isEqualTo(employeeDto.getAge());
    }

    @Test
    public void getUniversitiesTest(){

        List<UniversityDto> listOfUniversity = new ArrayList<>();

        listOfUniversity.add(new UniversityDto(List.of("marywood.edu"),"United States","US",List.of("http://www.marywood.edu"),"Marywood University"));
        listOfUniversity.add(new UniversityDto(List.of("lindenwood.edu"),"United States","US",List.of("http://www.lindenwood.edu/"),"Lindenwood University"));

        Flux<UniversityDto> employeeFlux = Flux.fromIterable(listOfUniversity);
        when(universityService.getAllUniversities()).thenReturn(employeeFlux);

        Flux<UniversityDto> responseBody=webTestClient.get().uri("/getAll")
                .exchange()
                .expectStatus().isOk()
                .returnResult(UniversityDto.class)
                .getResponseBody();

        StepVerifier.create(responseBody)
                .expectSubscription()
                .expectNext(new UniversityDto(List.of("marywood.edu"),"United States","US",List.of("http://www.marywood.edu"),"Marywood University"))
                .expectNext(new UniversityDto(List.of("lindenwood.edu"),"United States","US",List.of("http://www.lindenwood.edu/"),"Lindenwood University"))
                .verifyComplete();
    }


}

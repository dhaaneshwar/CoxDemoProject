package com.cox.coxDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("universities")
public class Universities {

//    @Id
//    private String id;
    @Id
    private String name;
    private List<String> domains;
    private List<String> web_pages;
    private String country;
    private String alpha_two_code;

    
}

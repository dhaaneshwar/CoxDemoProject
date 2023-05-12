package com.cox.coxDemo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UniversityDto {


    private List<String> domains;
    private String country;
    private String alpha_two_code;
    private List<String> web_pages;
    private String name;
}

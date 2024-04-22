package com.example.lab.model.dto;

import lombok.Data;

@Data
public class CountryDto {
    private String name;
    private String continent;

    public CountryDto() {
    }
}

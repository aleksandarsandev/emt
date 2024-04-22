package com.example.lab.model.dto;

import com.example.lab.model.Country;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class HostDto {
    private String name;
    private String surname;
    private Long countryId;

    public HostDto() {
    }

    public HostDto(String name, String surname, Long countryId) {
        this.name = name;
        this.surname = surname;
        this.countryId = countryId;
    }
}

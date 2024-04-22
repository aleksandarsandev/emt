package com.example.lab.model.dto;

import com.example.lab.model.Category;
import com.example.lab.model.Host;
import lombok.Data;

@Data
public class AccommodationDto {
    private String name;
    private Category category;
    Long  hostId;
    private Integer numRooms;

    public AccommodationDto() {
    }
}

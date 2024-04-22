package com.example.lab.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Accommodation {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Host host;
    private Integer numRooms;

    public Accommodation(String name, Category category, Host author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.host = author;
        this.numRooms = availableCopies;
    }

    public Accommodation() {
    }
}

package com.example.lab.service;

import com.example.lab.model.Accommodation;
import com.example.lab.model.Category;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> listAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> create(String name, Category category, Long hostId, Integer availableNights);
    Optional<Accommodation> update(Long id, String name, Category category, Long hostId, Integer availableNights);
    Optional<Accommodation> delete(Long id);
    Optional<Accommodation> numRoomsLower(Long id);
    List<Accommodation> filterByCategory(String category);
}

package com.example.lab.repository;

import com.example.lab.model.Accommodation;
import com.example.lab.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation,Long> {
    List<Accommodation> findByCategory(Category category);
}

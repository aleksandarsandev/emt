package com.example.lab.service.impl;

import com.example.lab.model.Accommodation;
import com.example.lab.model.Category;
import com.example.lab.model.Host;
import com.example.lab.repository.AccommodationRepository;
import com.example.lab.service.AccommodationService;
import com.example.lab.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository,HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
    }

    @Override
    public List<Accommodation> listAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return Optional.of(accommodationRepository.findById(id)).orElseThrow();
    }

    @Override
    public Optional<Accommodation> create(String name, Category category, Long hostId, Integer numRooms) {
        return Optional.of(accommodationRepository.save(new Accommodation(name,category,
                hostService.findById(hostId).orElseThrow(),numRooms)));
    }

    @Override
    public Optional<Accommodation> update(Long id, String name, Category category, Long hostId, Integer numRooms) {
        Accommodation accommodation=this.findById(id).get();
        accommodation.setName(name);
        accommodation.setCategory(category);
        accommodation.setNumRooms(numRooms);
        Host host=hostService.findById(hostId).get();
        accommodation.setHost(host);
        return Optional.of(accommodationRepository.save(accommodation));
    }

    @Override
    public Optional<Accommodation> delete(Long id) {
        Accommodation accommodation=this.findById(id).orElseThrow();
        accommodationRepository.delete(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public Optional<Accommodation> numRoomsLower(Long id) {
        Accommodation accommodation=this.findById(id).orElseThrow();
        accommodation.setNumRooms(accommodation.getNumRooms()-1);
        return Optional.of(accommodationRepository.save(accommodation));
    }

    @Override
    public List<Accommodation> filterByCategory(String category) {
        return  listAll().stream().filter(x->x.getCategory().name().equals(category)).toList();
    }

}

package com.example.lab.service.impl;

import com.example.lab.model.Country;
import com.example.lab.repository.CountryRepository;
import com.example.lab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return Optional.of(countryRepository.findById(id)).orElseThrow();
    }

    @Override
    public Optional<Country> create(String name, String continent) {
        return Optional.of(countryRepository.save(new Country(name,continent)));
    }

    @Override
    public Optional<Country> update(Long id, String name, String continent) {
        Country country=this.findById(id).orElseThrow();
        country.setName(name);
        country.setContinent(continent);
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> delete(Long id) {
        Country country=this.findById(id).orElseThrow();
        countryRepository.delete(country);
        return Optional.of(country);
    }
}

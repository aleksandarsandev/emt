package com.example.lab.service.impl;

import com.example.lab.model.Country;
import com.example.lab.model.Host;
import com.example.lab.repository.HostRepository;
import com.example.lab.service.CountryService;
import com.example.lab.service.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Host> listAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return Optional.of(hostRepository.findById(id).orElseThrow());
    }

    @Override
    public Optional<Host> create(String name, String surname, Long countryId) {
        return Optional.of(hostRepository.save(new Host(name,surname,countryService.findById(countryId).orElseThrow())));
    }

    @Override
    public Optional<Host> update(Long id, String name, String surname, Long countryId) {
        Host host=this.findById(id).orElseThrow();
        host.setName(name);
        host.setSurname(surname);
        Country country=countryService.findById(countryId).orElseThrow();
        host.setCountry(country);
        return Optional.of(hostRepository.save(host));
    }

    @Override
    public Optional<Host> delete(Long id) {
        Host host=this.findById(id).orElseThrow();
        hostRepository.delete(host);
        return Optional.of(host);
    }
}

package com.example.lab.web;
import com.example.lab.AccommodationEventPublisher;
import com.example.lab.listener.AccommodationEventListener;
import com.example.lab.model.Accommodation;
import com.example.lab.model.Category;
import com.example.lab.model.dto.AccommodationDto;
import com.example.lab.model.event.AccommodationEvent;
import com.example.lab.model.event.MessageEvent;
import com.example.lab.service.AccommodationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;
    private final AccommodationEventListener eventListener;
    private final AccommodationEventPublisher eventPublisher;

    public AccommodationController(AccommodationService accommodationService, AccommodationEventListener eventListener, AccommodationEventPublisher eventPublisher) {
        this.accommodationService = accommodationService;

        this.eventListener = eventListener;
        this.eventPublisher = eventPublisher;
    }
    @GetMapping()
    public List<Accommodation> getAllAccommodations(){
       // if(category==null) {
            return accommodationService.listAll();


    }
    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getAccommodation(@PathVariable Long id)
    {
        return accommodationService.findById(id).map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public ResponseEntity<Accommodation> addAccommodation(@RequestBody AccommodationDto accommodationDto)
    {
        return accommodationService.create(accommodationDto.getName(),
                        accommodationDto.getCategory(),
                        accommodationDto.getHostId(),
                        accommodationDto.getNumRooms())
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Accommodation> editAccommodation(@PathVariable Long id, @RequestBody AccommodationDto accommodationDto)
    {
        return accommodationService.update(id,
                        accommodationDto.getName(),
                        accommodationDto.getCategory(),
                        accommodationDto.getHostId(),
                        accommodationDto.getNumRooms())
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Accommodation> deleteAccommodation(@PathVariable Long id)
    {
        return accommodationService.delete(id)
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/lowerAvailableNights/{id}")
    public ResponseEntity<Accommodation> numRoomsLower(@PathVariable Long id) {
        return accommodationService.numRoomsLower(id)
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Accommodation>> filterAccomodations(@RequestParam (required = false) String category){
        List<Accommodation> filteredAccomodations=accommodationService.filterByCategory(category);
        if(filteredAccomodations.isEmpty()){
            return eventListener.handleAccommodationEvent(new AccommodationEvent(this,category));
        }
        else{
            return ResponseEntity.ok(filteredAccomodations);
        }
    }
}

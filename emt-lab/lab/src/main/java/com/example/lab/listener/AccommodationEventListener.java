package com.example.lab.listener;

import com.example.lab.model.Accommodation;
import com.example.lab.model.Category;
import com.example.lab.model.event.AccommodationEvent;
import com.sun.net.httpserver.HttpsServer;
import org.apache.coyote.Response;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class AccommodationEventListener {


    @EventListener
    public ResponseEntity<List<Accommodation>> handleAccommodationEvent(AccommodationEvent event) {
       String category=event.getCategory();
        System.out.printf("No accomodations found for category: %s%n",category);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
    }
}

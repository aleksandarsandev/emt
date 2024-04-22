package com.example.lab;

import com.example.lab.model.Accommodation;
import com.example.lab.model.event.AccommodationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AccommodationEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public AccommodationEventPublisher(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void publishEvent(Object event) {
        eventPublisher.publishEvent(event);
    }
}

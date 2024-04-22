package com.example.lab.model.event;

import com.example.lab.model.Accommodation;
import com.example.lab.model.Category;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class AccommodationEvent extends ApplicationEvent {
    private final String category;

    public AccommodationEvent(Object source, String category) {
        super(source);
        this.category = category;
    }

}
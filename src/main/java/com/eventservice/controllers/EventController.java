package com.eventservice.controllers;


import com.eventservice.exceptions.EntityNotFoundException;
import com.eventservice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventservice;

    @Autowired
    public EventController(@Qualifier("eventService") EventService eventservice) {
        this.eventservice = eventservice;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getEvents(@PathVariable("id") String id) throws EntityNotFoundException {
        return new ResponseEntity<>(eventservice.getEvents(id), HttpStatus.OK);
    }

}

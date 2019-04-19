package com.eventservice.controllers;


import com.eventservice.exceptions.EntityNotFoundException;
import com.eventservice.models.ErrorDetails;
import com.eventservice.models.Event;
import com.eventservice.services.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/event")
@Api(value = "event", description = "Operations for entities events management ")
public class EventController {

    private final EventService eventservice;

    @Autowired
    public EventController(@Qualifier("eventService") EventService eventservice) {
        this.eventservice = eventservice;
    }


    @ApiOperation(value = "Create Department", response = Event.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Events for id found successfully", response = Event.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "event %s not found", response = ErrorDetails.class)
    }
    )
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEvents(@PathVariable("id") String id) throws EntityNotFoundException {
        return new ResponseEntity<>(eventservice.getEvents(id), HttpStatus.OK);
    }

}

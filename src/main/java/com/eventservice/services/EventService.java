package com.eventservice.services;

import com.eventservice.exceptions.EntityNotFoundException;
import com.eventservice.models.Event;

import java.util.List;

public interface EventService {

    List<Event> getEvents(String id) throws EntityNotFoundException;

}

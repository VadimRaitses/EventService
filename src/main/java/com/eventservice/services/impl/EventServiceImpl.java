package com.eventservice.services.impl;

import com.eventservice.dao.DaoRepository;
import com.eventservice.exceptions.EntityNotFoundException;
import com.eventservice.models.Event;
import com.eventservice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final DaoRepository<Event> repository;

    @Autowired
    public EventServiceImpl(DaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Event> getEvents(String id) throws EntityNotFoundException {
        return Optional.of(repository.getByIdSortedByTime(id, Event.class))
                .map(e -> {
                    if (e.size() > 0)
                        return e;
                    return null;
                }).orElseThrow(() -> new EntityNotFoundException(String.format("event %s not found", id)));
    }
}

package com.eventservice.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "events")
public class Event {

    private String entityId;
    private String state;
    private Date date;

    public Event() {
    }

    public Event(String entityId, String state, Date date) {
        this.entityId = entityId;
        this.state = state;
        this.date = date;
    }

    public String getEntityId() {
        return entityId;
    }

    public Event setEntityId(String entityId) {
        this.entityId = entityId;
        return this;
    }

    public String getState() {
        return state;
    }

    public Event setState(String state) {
        this.state = state;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Event setDate(Date date) {
        this.date = date;
        return this;
    }
}


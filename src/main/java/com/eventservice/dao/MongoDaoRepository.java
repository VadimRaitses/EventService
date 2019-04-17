package com.eventservice.dao;


import com.eventservice.models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MongoDaoRepository implements DaoRepository<Event> {

    private final MongoTemplate template;

    @Autowired
    public MongoDaoRepository(MongoTemplate template) {
        this.template = template;
    }

    @Override
    public List<Event> getByIdSortedByTime(String id, Class entityClass) {
        return template.find(Query.query(new Criteria("entityId").is(id))
                .with(new Sort(Sort.Direction.ASC, "date")), Event.class);
    }

    @Override
    public Event save(Event o, String collectionName) {
        return template.save(o, collectionName);
    }


}

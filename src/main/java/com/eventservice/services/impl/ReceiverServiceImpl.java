package com.eventservice.services.impl;

import com.eventservice.dao.DaoRepository;
import com.eventservice.models.Event;
import com.eventservice.services.ReceiverService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RabbitListener(queues = "#{@rabbitMqProperties.getTopicExchangeName()}")
public class ReceiverServiceImpl implements ReceiverService {


    private final DaoRepository<Event> repository;
    private final Gson gson;
    private final String queueName;

    @Autowired
    public ReceiverServiceImpl(DaoRepository repository,
                               Gson gson,
                               @Qualifier("eventCollection") String queueName) {
        this.repository = repository;
        this.gson = gson;
        this.queueName = queueName;
    }

    @RabbitHandler
    public void receiveMessage(String message) {
        Event event = gson.fromJson(message, Event.class);
        repository.save(event.setDate(new Date()), queueName);
        System.out.println("Received <" + message + ">");
    }


}

package com.eventservice.config;

import com.eventservice.dao.DaoRepository;
import com.eventservice.dao.MongoDaoRepository;
import com.eventservice.services.EventService;
import com.eventservice.services.impl.EventServiceImpl;
import com.eventservice.services.impl.ReceiverServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author Raitses Vadim
 */


@Configuration
public class AppConfig {


    @Bean
     MongoProperties mongoProperties(){
       return  new MongoProperties();
    }

    @Bean
      RabbitMqProperties rabbitMqProperties(){
       return  new RabbitMqProperties();
    }

    @Bean
    public MongoClient mongo() {
        return new MongoClient(mongoProperties().getHost(), mongoProperties().getPort());
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), mongoProperties().getDatabase());
    }

    @Bean
    Queue queue() {
        return new Queue(rabbitMqProperties().getQueueName());
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(rabbitMqProperties().getHost());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }

    @Bean("eventService")
    public EventService eventService() {
        return new EventServiceImpl(mongoRepositoryDao());
    }

    @Bean("eventCollection")
    public String eventCollectionName() {
        return rabbitMqProperties().getEventCollection();
    }


    @Bean
    Gson gson() {
      return  new Gson();
    }

    @Bean
    JsonParser parser(){
        return new JsonParser();
    }

    @Primary
    @Bean
    public DaoRepository mongoRepositoryDao() {
        return new MongoDaoRepository(mongoTemplate());

    }


}

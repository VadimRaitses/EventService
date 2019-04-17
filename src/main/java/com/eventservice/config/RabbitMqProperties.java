package com.eventservice.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component("rabbitMqProperties")
@ConfigurationProperties("rabbitmq")
public class RabbitMqProperties {

    private String topicExchangeName;
    private String queueName;
    private String host;
    private Integer port;
    private String eventCollection;


    public String getTopicExchangeName() {
        return topicExchangeName;
    }

    public void setTopicExchangeName(String topicExchangeName) {
        this.topicExchangeName = topicExchangeName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(String eventCollection) {
        this.eventCollection = eventCollection;
    }
}

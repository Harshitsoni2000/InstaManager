package com.instagrammanager.instagram.RabbitMQ.Config;

import com.instagrammanager.instagram.ConfigurationFile;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    @Autowired
    private ConfigurationFile config;

    @Bean
    public Queue commentQueue() {
        return new Queue(config.getCOMMENT_QUEUE());
    }

    @Bean
    public Queue postQueue() {
        return new Queue(config.getPOST_QUEUE());
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(config.getEXCHANGE());
    }

    @Bean
    public Binding commentBinding(Queue commentQueue, TopicExchange exchange) {
        return BindingBuilder.bind(commentQueue).to(exchange).with(config.getCOMMENT_ROUTING_KEY());
    }

    @Bean
    public Binding postBinding(Queue postQueue, TopicExchange exchange) {
        return BindingBuilder.bind(postQueue).to(exchange).with(config.getPOST_ROUTING_KEY());
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}

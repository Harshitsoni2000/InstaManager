package com.instagrammanager.instagram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter @Setter
public class ConfigurationFile {

    // # RabbitMQ Config
    @Value("${COMMENT_QUEUE}")
    private String COMMENT_QUEUE;
    @Value("${POST_QUEUE}")
    private String POST_QUEUE;
    @Value("${EXCHANGE}")
    private String EXCHANGE;
    @Value("${COMMENT_ROUTING_KEY}")
    private String COMMENT_ROUTING_KEY;
    @Value("${POST_ROUTING_KEY}")
    private String POST_ROUTING_KEY;
}

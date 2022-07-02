package com.instagrammanager.instagram.RabbitMQ.Publisher;

import com.instagrammanager.instagram.Constants.Constant;
import com.instagrammanager.instagram.Model.Post;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PostPublisher {
    
    @Autowired
    public RabbitTemplate postTemplate;
    
    public void postPublish(Post post) {
        // Send Post to Post_Queue
        postTemplate.convertAndSend(Constant.exchange, Constant.postRoutingKey, post);
    }
}

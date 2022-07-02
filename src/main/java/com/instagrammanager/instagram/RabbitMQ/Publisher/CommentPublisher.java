package com.instagrammanager.instagram.RabbitMQ.Publisher;

import com.instagrammanager.instagram.Constants.Constant;
import com.instagrammanager.instagram.Model.Comment;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentPublisher {
    
    @Autowired
    public RabbitTemplate commentTemplate;

    public void publishComment(Comment comment) {
        // Send Comment to Comment_Queue
        commentTemplate.convertAndSend(Constant.exchange, Constant.commentRoutingKey, comment);
    }
    
}
